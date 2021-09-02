package br.com.zupacademy.ifzup.proposta.cartao;

import br.com.zupacademy.ifzup.proposta.proposta.Proposta;
import br.com.zupacademy.ifzup.proposta.proposta.PropostaRepository;
import feign.FeignException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
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

    @Scheduled(fixedDelay = 30000)
    public void associaCartao() {
      //  List<Proposta> propostasElegiveis = propostaRepository.findByStatus(ELEGIVEL);
        List<Proposta> propostasElegiveis = propostaRepository.findByStatusAndCartaoIsNull(ELEGIVEL);
        propostasElegiveis.forEach(System.out::println);
        if (!propostasElegiveis.isEmpty()) {
            for (Proposta proposta : propostasElegiveis) {

                try{
                    AnalisaCartaoResponse response = cartaoClient.associaCartao(proposta.getId()).getBody();
                    System.out.println(response.toString());
                    Cartao cartao = response.paraCartao(proposta);
                    proposta.associarCartao(cartao);
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
