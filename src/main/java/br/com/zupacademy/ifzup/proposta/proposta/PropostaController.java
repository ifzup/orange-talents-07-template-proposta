package br.com.zupacademy.ifzup.proposta.proposta;

import br.com.zupacademy.ifzup.proposta.analise.AnalisaPropostaRequest;
import br.com.zupacademy.ifzup.proposta.analise.AnalisaPropostaResponse;
import br.com.zupacademy.ifzup.proposta.analise.AnalisaSolicitacaoClient;
import br.com.zupacademy.ifzup.proposta.analise.Status;
import feign.FeignException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import javax.validation.Valid;
import java.net.URI;

import static br.com.zupacademy.ifzup.proposta.analise.Status.ELEGIVEL;
import static br.com.zupacademy.ifzup.proposta.analise.Status.NAO_ELEGIVEL;

@RestController
@RequestMapping("/api/propostas")
public class PropostaController {


    @PersistenceContext
    EntityManager manager;

    @Autowired
    PropostaRepository propostaRepository;

    @Autowired
    private AnalisaSolicitacaoClient analisaSolicitacaoClient;

    @Transactional
    @PostMapping("/criar")
    public ResponseEntity<?> cadastrar(@RequestBody @Valid PropostaRequest request, UriComponentsBuilder uriBuilder) {
        Proposta proposta = request.converter();


        if (propostaRepository.existsByDocumento(request.getDocumento())) {
            return ResponseEntity.unprocessableEntity().body("Documento já utilizado");
        }
        propostaRepository.save(proposta);

        /*
        try{
            AnalisaPropostaRequest analiseRequest = new AnalisaPropostaRequest(proposta.getDocumento(), proposta.getNome(), proposta.getId());

            AnalisaPropostaResponse resultadoDaConsulta = analisaSolicitacaoClient.consulta(analiseRequest);
            Status status=resultadoDaConsulta.status();

            proposta.setStatus(status);
        }catch (FeignException.UnprocessableEntity unprocessableEntity){

            proposta.setStatus(Status.NAO_ELEGIVEL);
        }catch(FeignException.ServiceUnavailable ex){
            propostaRepository.delete(proposta);
        }*/

        try {
            analisaSolicitacaoClient.consulta(new AnalisaPropostaRequest(proposta.getDocumento(),
                    proposta.getNome(),
                    Long.toString(proposta.getId())));
            proposta.setStatus(NAO_ELEGIVEL);
        } catch (FeignException e) {
            //retornou 4xx ou 5xx, cliente tem restricao
            proposta.setStatus(ELEGIVEL);
        }

        URI enderecoCadastro = uriBuilder.path("/proposta/{id}").buildAndExpand(proposta.getId()).toUri();
        return ResponseEntity.created(enderecoCadastro).build();
    }

}
