package br.com.zupacademy.ifzup.proposta.analise;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;

@FeignClient(name = "analise-solicitacao", url = "http://localhost:9999")
public interface AnalisaSolicitacaoClient {

    @PostMapping("/api/solicitacao")
    AnalisaPropostaResponse consulta(AnalisaPropostaRequest request);
}
