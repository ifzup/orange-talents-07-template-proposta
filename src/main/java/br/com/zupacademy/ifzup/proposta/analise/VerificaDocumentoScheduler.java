package br.com.zupacademy.ifzup.proposta.analise;

import br.com.zupacademy.ifzup.proposta.proposta.Proposta;
import br.com.zupacademy.ifzup.proposta.proposta.PropostaRepository;
import br.com.zupacademy.ifzup.proposta.proposta.PropostaRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import java.util.ArrayList;
import java.util.List;

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
            analisaSolicitacaoClient.consultaFeign(request);
        }
    }
}
