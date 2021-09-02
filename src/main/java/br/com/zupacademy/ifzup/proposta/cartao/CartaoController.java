package br.com.zupacademy.ifzup.proposta.cartao;

import br.com.zupacademy.ifzup.proposta.biometria.Biometria;
import br.com.zupacademy.ifzup.proposta.biometria.BiometriaRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.servlet.http.HttpServletRequest;
import javax.transaction.Transactional;
import javax.validation.Valid;
import java.net.URI;
import java.util.List;


@RestController
@RequestMapping("/cartoes")
public class CartaoController {


    @PersistenceContext
    EntityManager manager;
    @Autowired
    CartaoRepository cartaoRepository;

    public final Logger logger = LoggerFactory.getLogger(CartaoController.class);

    @Autowired
    HttpServletRequest request;

    @PostMapping("/biometria/{id}")
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

    @PostMapping("/bloqueio/{idCartao}")
    @Transactional
    public ResponseEntity<Bloqueio> bloquearCartao(@PathVariable ("idCartao") Long idCartao){

        Cartao cartao = manager.find(Cartao.class, idCartao);
        //Cartao cartao = cartaoRepository.findById(idCartao);
        if(cartao==null){
            return ResponseEntity.badRequest().build();
        }

        List<Bloqueio> bloqueiosCartao = cartao.getBloqueios();
        for (Bloqueio bloqueio : bloqueiosCartao){
            if(bloqueio.isAtivo())
                logger.info("Cartão com bloqueio já ativo");
            return  ResponseEntity.badRequest().build();
        }

        String ipSolicitante = request.getRemoteHost();
        String userAgent = request.getHeader("User-Agent");

        Bloqueio novoBloqueio = new Bloqueio (userAgent, ipSolicitante, cartao, true, "apiPropostas");

        logger.info("Bloqueando o cartão");
        manager.persist(novoBloqueio);

        return ResponseEntity.ok().build();
    }
}
