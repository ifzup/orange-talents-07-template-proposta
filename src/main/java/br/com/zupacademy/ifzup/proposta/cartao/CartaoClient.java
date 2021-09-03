package br.com.zupacademy.ifzup.proposta.cartao;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@FeignClient(name = "analise-cartao", url = "http://localhost:8888")
public interface CartaoClient {

    @RequestMapping(method = RequestMethod.GET, value="/api/cartoes", consumes = "application/json")
    ResponseEntity<AnalisaCartaoResponse> associaCartao(@RequestParam Long idProposta);

    @RequestMapping(method = RequestMethod.POST, value="/{id}/bloqueios", consumes = "application/json")
    ResponseEntity<ResultadoBloqueio> solicitaBloqueio(@RequestBody SolicitacaoBloqueio solicitacaoBloqueio, @PathVariable("numeroCartao") String numeroCartao);
}

