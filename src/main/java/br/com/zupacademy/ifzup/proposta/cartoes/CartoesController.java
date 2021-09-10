package br.com.zupacademy.ifzup.proposta.cartoes;

import br.com.zupacademy.ifzup.proposta.biometria.Biometria;
import br.com.zupacademy.ifzup.proposta.biometria.BiometriaRequest;
import br.com.zupacademy.ifzup.proposta.cartoes.aviso.Aviso;
import br.com.zupacademy.ifzup.proposta.cartoes.aviso.AvisoRequest;
import br.com.zupacademy.ifzup.proposta.cartoes.aviso.AvisoViagemRepository;
import br.com.zupacademy.ifzup.proposta.cartoes.aviso.AvisoViagemResult;
import br.com.zupacademy.ifzup.proposta.cartoes.bloqueio.Bloqueio;
import br.com.zupacademy.ifzup.proposta.cartoes.bloqueio.ResultadoBloqueio;
import br.com.zupacademy.ifzup.proposta.cartoes.bloqueio.SolicitacaoBloqueio;
import br.com.zupacademy.ifzup.proposta.cartoes.cartao.Cartao;
import br.com.zupacademy.ifzup.proposta.cartoes.cartao.CartaoRepository;
import feign.FeignException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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
public class CartoesController {


    @PersistenceContext
    EntityManager manager;
    @Autowired
    CartaoRepository cartaoRepository;
    @Autowired
    CartoesClient cartoesClient;
    @Autowired
    AvisoViagemRepository avisoViagemRepository;

    public final Logger logger = LoggerFactory.getLogger(CartoesController.class);

    @Autowired
    HttpServletRequest request;

    @PostMapping("/biometria/{id}")
    @Transactional
    public ResponseEntity<Biometria> cadastrarBiometria(@PathVariable("id") Long id, @RequestBody @Valid BiometriaRequest request, UriComponentsBuilder uriBuilder) {

        Cartao cartao = manager.find(Cartao.class, id);
        // Optional<Cartao> cartao = cartaoRepository.findById(id);
        if (cartao == null) {
            return ResponseEntity.notFound().build();
        } else {
            Biometria biometria = new Biometria(request, cartao);

            manager.persist(biometria);

            URI location = uriBuilder.path("/biometria/{id}")
                    .buildAndExpand(biometria.getId())
                    .toUri();
            return ResponseEntity.created(location).build();
        }
    }

    @PostMapping("/bloqueio/{idCartao}")
    @Transactional
    public ResponseEntity<Bloqueio> bloquearCartao(@PathVariable("idCartao") Long idCartao) {

        Cartao cartao = manager.find(Cartao.class, idCartao);
        ResultadoBloqueio resultadoBloqueio = new ResultadoBloqueio();
        if (cartao == null) {
            return ResponseEntity.badRequest().build();
        }

        List<Bloqueio> bloqueiosCartao = cartao.getBloqueios();
        for (Bloqueio bloqueio : bloqueiosCartao) {
            if (bloqueio.isAtivo())
                logger.info("Cartão com bloqueio já ativo");
            return ResponseEntity.badRequest().build();
        }

        String ipSolicitante = request.getRemoteHost();
        String userAgent = request.getHeader("User-Agent");

        SolicitacaoBloqueio solicitacaoBloqueio = new SolicitacaoBloqueio("Proposta");

        Bloqueio novoBloqueio = new Bloqueio(userAgent, ipSolicitante, cartao, true, solicitacaoBloqueio.getSistemaResponsavel());
        try {
            resultadoBloqueio = cartoesClient.solicitaBloqueio(solicitacaoBloqueio, cartao.getNumeroCartao()).getBody();
        } catch (FeignException e) {
            logger.info("Feign exception");

            if (e.status() == HttpStatus.UNPROCESSABLE_ENTITY.value()) {
                novoBloqueio.setAtivo(false);
                logger.info("API cartao retornou FALHA.");
            }
        }

        logger.info("Bloqueando o cartão");
        manager.persist(novoBloqueio);

        return ResponseEntity.ok().build();
    }

    @Transactional
    @PostMapping("/{numeroCartao}/avisos")
    public ResponseEntity<AvisoViagemResult> resgistrarAvisoViagem(@PathVariable("numeroCartao") String numeroCartao, @RequestBody AvisoRequest avisoRequest) {
        AvisoViagemResult resultado = new AvisoViagemResult();


        Cartao cartao = cartaoRepository.findByNumeroCartao(numeroCartao);

        String ipSolicitante = request.getRemoteHost();
        String userAgent = request.getHeader("User-Agent");

        if (cartao == null) {
            System.out.println("ERROOOOOOOOOOOOOOOOOOOOOOOOOOOOOOO AQUIII");
            logger.info("Cartão não encontrado");
            return ResponseEntity.notFound().build();
        }
        try {
            resultado = cartoesClient.solicitarAviso(avisoRequest, numeroCartao).getBody();
            Aviso aviso = new Aviso(avisoRequest, cartao, userAgent, ipSolicitante);
            manager.persist(aviso);
        } catch (FeignException fe) {
            System.out.println(resultado);
            logger.info("Feign exception");

            if (fe.status() == HttpStatus.UNPROCESSABLE_ENTITY.value()) {
                assert resultado != null;
                if (resultado.equals("FALHA")) {
                    return ResponseEntity.badRequest().body(resultado);
                }
            }
            logger.info("Retornou FALHA");
        }

        return ResponseEntity.ok().body(resultado);
    }
}
