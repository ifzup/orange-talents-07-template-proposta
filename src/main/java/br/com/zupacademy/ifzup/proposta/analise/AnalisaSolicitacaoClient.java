package br.com.zupacademy.ifzup.proposta.analise;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Component
@FeignClient(name = "analise-solicitacao", url = "http://localhost:9999")
public interface AnalisaSolicitacaoClient {

    @RequestMapping(method = RequestMethod.POST, value="/api/solicitacao", consumes = "application/json")
    AnalisaPropostaResponse consultaFeign(AnalisaPropostaRequest request);
}
