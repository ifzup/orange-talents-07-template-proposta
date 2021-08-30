package br.com.zupacademy.ifzup.proposta.cartao;

import br.com.zupacademy.ifzup.proposta.proposta.Proposta;
import br.com.zupacademy.ifzup.proposta.proposta.PropostaRepository;
import feign.FeignException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import java.util.List;

import static br.com.zupacademy.ifzup.proposta.analise.Status.ASSOCIADO;
import static br.com.zupacademy.ifzup.proposta.analise.Status.ELEGIVEL;

@Configuration
@EnableScheduling
@EnableAsync
public class AssociaCartaoScheduler {

    @Autowired
    private PropostaRepository propostaRepository;

    @Autowired
    private CartaoRepository cartaoRepository;

    @Autowired
    private CartaoClient cartaoClient;

    @Scheduled(fixedDelay = 3000)
    public void associaCartao() {
        List<Proposta> propostasElegiveis = propostaRepository.findByStatus(ELEGIVEL);
                //Proposta.listarPropostasPorStatus(Status.ELEGIVEL, propostaRepository);
        //List<AnalisaCartaoRequest> solicitacaoDeCartao = new ArrayList<>();
        if (!propostasElegiveis.isEmpty()) {
            for (Proposta proposta : propostasElegiveis) {

                AnalisaCartaoRequest request = new AnalisaCartaoRequest(proposta);
                //solicitacaoDeCartao.add(request);
                try{
                    AnalisaCartaoResponse response = cartaoClient.associaCartao(proposta.getId());
                    assert response != null;
                    Cartao cartao = new Cartao(response, proposta);
                    proposta.setCartao(cartao);
                    proposta.setStatus(ASSOCIADO);
                    propostaRepository.save(proposta);
                    //cartaoRepository.save(cartao);
                }
                catch(FeignException fe){
                    System.out.println("Log falso: Feign exception");
                }
            }

        }
    }
}
