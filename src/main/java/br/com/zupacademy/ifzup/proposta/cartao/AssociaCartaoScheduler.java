package br.com.zupacademy.ifzup.proposta.cartao;

import br.com.zupacademy.ifzup.proposta.analise.Status;
import br.com.zupacademy.ifzup.proposta.proposta.Proposta;
import br.com.zupacademy.ifzup.proposta.proposta.PropostaRepository;
import feign.FeignException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import java.util.ArrayList;
import java.util.List;

import static br.com.zupacademy.ifzup.proposta.analise.Status.ASSOCIADO;

@Configuration
@EnableScheduling
@EnableAsync
public class AssociaCartaoScheduler {

    @Autowired
    private PropostaRepository propostaRepository;

    @Autowired
    private CartaoClient cartaoClient;

    @Scheduled(fixedDelay = 300000)
    public void associaCartao() {

        List<Proposta> propostasElegiveis = Proposta.listarPropostasPorStatus(Status.ELEGIVEL, propostaRepository);
        List<AnalisaCartaoRequest> solicitacaoDeCartao = new ArrayList<>();
        if (!propostasElegiveis.isEmpty()) {
            for (Proposta proposta : propostasElegiveis) {

                AnalisaCartaoRequest request = new AnalisaCartaoRequest(proposta);
                solicitacaoDeCartao.add(request);
                try{
                    AnalisaCartaoResponse response = cartaoClient.associaCartao(request).getBody();
                    assert response != null;
                    Cartao cartao = new Cartao(response, proposta);
                    proposta.setStatus(ASSOCIADO);
                    propostaRepository.save(proposta);
                }
                catch(FeignException fe){
                    System.out.println("Log falso: Feign exception");
                }
            }

        }

        /*for (AnalisaCartaoRequest request : solicitacaoDeCartao) {
            try{
                AnalisaCartaoResponse response = cartaoClient.associaCartao(request).getBody();
                Cartao cartao = new Cartao(response, proposta);
                proposta.setStatus();
            }
            catch(FeignException fe){
                System.out.println("Log falso: Feign exception");
            }
        }*/
    }
}
//Instanciar cartão com CartaoResponse.
//Persistir cartão.
//Atualizar o status da proposta. Add o Status de Associado/atualizado
//Criar um novo Status -----
//Persistir a proposta.