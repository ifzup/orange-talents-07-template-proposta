package br.com.zupacademy.ifzup.proposta.proposta;

import br.com.zupacademy.ifzup.proposta.analise.AnalisaPropostaRequest;
import br.com.zupacademy.ifzup.proposta.analise.AnalisaSolicitacaoClient;
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


        URI enderecoCadastro = uriBuilder.path("/proposta/{id}").buildAndExpand(proposta.getId()).toUri();
        return ResponseEntity.created(enderecoCadastro).build();
    }

}
