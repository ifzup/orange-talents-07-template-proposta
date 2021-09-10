package br.com.zupacademy.ifzup.proposta.proposta;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.persistence.EntityManager;

@RestController
@RequestMapping("/api/propostas")
public class PropostaControllerGet {

    @Autowired
    EntityManager manager;

    @GetMapping("/{id}")
    public ResponseEntity<PropostaResponse> listarPorId(@PathVariable("id") Long id) {
        Proposta proposta = manager.find(Proposta.class, id);

        if (proposta == null) {
            return ResponseEntity.notFound().build();
        }
        PropostaResponse propostaEncontrada = new PropostaResponse(proposta);
        return ResponseEntity.ok(propostaEncontrada);
    }
}
