package br.com.zupacademy.ifzup.proposta.biometria;

import br.com.zupacademy.ifzup.proposta.cartao.Cartao;
import br.com.zupacademy.ifzup.proposta.cartao.CartaoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import javax.validation.Valid;
import java.net.URI;
import java.util.Optional;

@RestController
@RequestMapping("/biometria")
public class BiometriaController {

    @PersistenceContext
    EntityManager manager;
    @Autowired
    CartaoRepository cartaoRepository;
    @PostMapping("/{id}")
    @Transactional
    public ResponseEntity<Biometria> cadastrarBiometria(@PathVariable("id") Long id, @RequestBody @Valid BiometriaRequest request, UriComponentsBuilder uriBuilder){

        Cartao cartao = manager.find(Cartao.class, id);
       // Optional<Cartao> cartao = cartaoRepository.findById(id);
        if(cartao == null){
            return ResponseEntity.notFound().build();
        }
        else{
        Biometria biometria = new Biometria(request, cartao);

        manager.persist(biometria);

        URI location = uriBuilder.path("/biometria/{id}")
                .buildAndExpand(biometria.getId())
                .toUri();
        return ResponseEntity.created(location).build();}
    }
}
