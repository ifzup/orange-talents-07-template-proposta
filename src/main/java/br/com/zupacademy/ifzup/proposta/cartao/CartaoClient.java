package br.com.zupacademy.ifzup.proposta.cartao;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


@FeignClient(name = "analise-cartao", url = "http://localhost:8888")
public interface CartaoClient {

    @RequestMapping(method = RequestMethod.POST, value="/api/cartoes", consumes = "application/json")
    ResponseEntity<AnalisaCartaoResponse> associaCartao(@RequestBody AnalisaCartaoRequest request);
}

