package br.com.zupacademy.ifzup.proposta.cartao;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;


@FeignClient(name = "analise-cartao", url = "http://localhost:8888")
public interface CartaoClient {

    @RequestMapping(method = RequestMethod.GET, value="/api/cartoes", consumes = "application/json")
    ResponseEntity<AnalisaCartaoResponse> associaCartao(@RequestParam Long idProposta);
}

