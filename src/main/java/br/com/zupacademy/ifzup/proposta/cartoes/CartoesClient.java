package br.com.zupacademy.ifzup.proposta.cartoes;

import br.com.zupacademy.ifzup.proposta.cartoes.aviso.AvisoRequest;
import br.com.zupacademy.ifzup.proposta.cartoes.aviso.AvisoViagemResult;
import br.com.zupacademy.ifzup.proposta.cartoes.bloqueio.ResultadoBloqueio;
import br.com.zupacademy.ifzup.proposta.cartoes.bloqueio.SolicitacaoBloqueio;
import br.com.zupacademy.ifzup.proposta.cartoes.cartao.AnalisaCartaoResponse;
import br.com.zupacademy.ifzup.proposta.cartoes.carteira.ResultadoCarteira;
import br.com.zupacademy.ifzup.proposta.cartoes.carteira.SolicitacaoInclusaoCarteira;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;


@FeignClient(name = "analise-cartao", url = "http://localhost:8888")
public interface CartoesClient {

    @RequestMapping(method = RequestMethod.GET, value = "/api/cartoes", consumes = "application/json")
    ResponseEntity<AnalisaCartaoResponse> associaCartao(@RequestParam Long idProposta);

    @RequestMapping(method = RequestMethod.POST, value = "/api/{id}/bloqueios", consumes = "application/json")
    ResponseEntity<ResultadoBloqueio> solicitaBloqueio(@RequestBody SolicitacaoBloqueio solicitacaoBloqueio, @PathVariable("numeroCartao") String numeroCartao);

    @RequestMapping(method = RequestMethod.POST, value = "/api/cartoes/{numeroCartao}/avisos", consumes = "application/json")
    ResponseEntity<AvisoViagemResult> solicitarAviso(@RequestBody @Valid AvisoRequest avisoRequest, @PathVariable String numeroCartao);

    @RequestMapping(method = RequestMethod.POST, value = "/api/cartoes/{numeroCartao}/carteiras", consumes = "application/json")
    ResponseEntity<ResultadoCarteira> associaCarteira(@RequestBody SolicitacaoInclusaoCarteira request, @PathVariable String numeroCartao);
}

