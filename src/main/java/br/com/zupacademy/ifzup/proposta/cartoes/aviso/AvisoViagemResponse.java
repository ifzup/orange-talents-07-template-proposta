package br.com.zupacademy.ifzup.proposta.cartoes.aviso;

import com.fasterxml.jackson.annotation.JsonAutoDetect;

@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
public class AvisoViagemResponse {
    private String resultado;

    public String getResultado() {
        return resultado;
    }
}
