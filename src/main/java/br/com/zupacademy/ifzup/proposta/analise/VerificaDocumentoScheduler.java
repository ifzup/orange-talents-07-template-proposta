package br.com.zupacademy.ifzup.proposta.analise;

import br.com.zupacademy.ifzup.proposta.proposta.Proposta;
import br.com.zupacademy.ifzup.proposta.proposta.PropostaRepository;
import br.com.zupacademy.ifzup.proposta.proposta.PropostaRequest;
import feign.FeignException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static br.com.zupacademy.ifzup.proposta.analise.Status.ELEGIVEL;
import static br.com.zupacademy.ifzup.proposta.analise.Status.NAO_ELEGIVEL;

@Configuration
@EnableScheduling
@EnableAsync
public class VerificaDocumentoScheduler {

    @Autowired
    private PropostaRepository propostaRepository;

    @Autowired
    private AnalisaSolicitacaoClient analisaSolicitacaoClient;

    @Scheduled(fixedDelay = 5000)
    public void analisaPropostas() {
        List<Proposta> propostasEmAnalise = propostaRepository.findByStatus(null);
        List<AnalisaPropostaRequest> solicitacoesPendentes = new ArrayList<>();

        if (!propostasEmAnalise.isEmpty()) {
            for (Proposta proposta : propostasEmAnalise) {
                AnalisaPropostaRequest analisaPropostaRequest = new AnalisaPropostaRequest(proposta);
                solicitacoesPendentes.add(analisaPropostaRequest);
            }
        }

        for ( AnalisaPropostaRequest request : solicitacoesPendentes) {
            Proposta proposta = propostaRepository.findById(request.getIdPropostaLong()).get();
            try {
                analisaSolicitacaoClient.consultaFeign(request);
                proposta.setStatus(ELEGIVEL);
            }
            catch (FeignException fe){
                if(fe.status() == HttpStatus.UNPROCESSABLE_ENTITY.value())
                    proposta.setStatus(NAO_ELEGIVEL);
            }
            propostaRepository.save(proposta);
        }
    }
}
