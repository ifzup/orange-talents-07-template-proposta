package br.com.zupacademy.ifzup.proposta.cartao;

import br.com.zupacademy.ifzup.proposta.biometria.Biometria;
import br.com.zupacademy.ifzup.proposta.biometria.BiometriaRequest;
import feign.FeignException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
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
    @Autowired
    CartaoClient cartaoClient;
    @Autowired
    AvisoViagemRepository  avisoViagemRepository;

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
        ResultadoBloqueio resultadoBloqueio = new ResultadoBloqueio();
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

        SolicitacaoBloqueio solicitacaoBloqueio = new SolicitacaoBloqueio("Proposta");
        Bloqueio novoBloqueio = new Bloqueio (userAgent, ipSolicitante, cartao, true, solicitacaoBloqueio.getSistemaResponsavel());

        try {
            resultadoBloqueio = cartaoClient.solicitaBloqueio(solicitacaoBloqueio, cartao.getNumeroCartao()).getBody();
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
/*

    @PostMapping("/{id}/avisos")
    public ResponseEntity<?> noficaViagem(@PathVariable("id") long id, @RequestBody @Valid AvisoRequest notificaRequest,
                                          @RequestHeader HttpHeaders headers, HttpServletRequest httpRequest){

        Cartao cartao = cartaoRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Cartão não encontrado"));

        String ipCliente = httpRequest.getRemoteAddr();
        String userAgent = headers.get(HttpHeaders.USER_AGENT).get(0);

        AvisoViagemRequest avisoViagemRequest = new AvisoViagemRequest(notificaRequest.getDestino(), notificaRequest.getValidoAte());
        AvisoViagemResponse avisoViagemResponse = cartaoClient.notificacaoViagem(cartao.getNumeroCartao(), avisoViagemRequest);

        if(avisoViagemResponse.getResultado().equals("CRIADO")){
            Aviso aviso = notificaRequest.toModel(ipCliente, userAgent, cartao);
            cartao.adcionaAviso(aviso);
            avisoViagemRepository.save(aviso);
            return ResponseEntity.ok().build();
        }

        throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Houve um erro ao notificar o sistema bancário");
    }

*/


    @Transactional
    @PostMapping("/{numeroCartao}/avisos")
    public ResponseEntity<AvisoViagemResult> resgistrarAvisoViagem(@PathVariable("numeroCartao") String numeroCartao, @RequestBody AvisoRequest avisoRequest){
        AvisoViagemResult resultado = new AvisoViagemResult();


        Cartao cartao = cartaoRepository.findByNumeroCartao(numeroCartao);

        String ipSolicitante = request.getRemoteHost();
        String userAgent = request.getHeader("User-Agent");

        if(cartao == null){
            System.out.println("ERROOOOOOOOOOOOOOOOOOOOOOOOOOOOOOO AQUIII");
            logger.info("Cartão não encontrado");
            return ResponseEntity.notFound().build();
        }
        try{
            resultado = cartaoClient.solicitarAviso(avisoRequest, numeroCartao).getBody();
            Aviso aviso = new Aviso(avisoRequest, cartao, userAgent, ipSolicitante);
            manager.persist(aviso);
        } catch(FeignException fe){
            System.out.println(resultado);
            logger.info("Feign exception");

            if(fe.status() == HttpStatus.UNPROCESSABLE_ENTITY.value()) {
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
