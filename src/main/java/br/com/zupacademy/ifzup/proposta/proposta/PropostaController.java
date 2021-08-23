package br.com.zupacademy.ifzup.proposta.proposta;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.validation.Valid;
import java.net.URI;

@RestController
@RequestMapping("/propostas")
public class PropostaController {


    @PersistenceContext
    EntityManager manager;

    @Autowired
    PropostaRepository propostaRepository;

    @PostMapping("/criar")
        public ResponseEntity<?> cadastrar(@RequestBody @Valid PropostaRequest request, UriComponentsBuilder uriBuilder) {
        Proposta proposta = request.converter();
        propostaRepository.save(proposta);

        //Retorna 201 com Header Location preenchido com a URL da nova proposta em caso de sucesso.
        URI enderecoCadastro = uriBuilder.path("/proposta/{id}").buildAndExpand(proposta.getId()).toUri();
        return ResponseEntity.created(enderecoCadastro).build();
    }



}
