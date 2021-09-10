package br.com.zupacademy.ifzup.proposta.cartoes.carteira;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;

public class ResultadoCarteira {
    @Enumerated(EnumType.STRING)
    ResultadoCarteira resultado;
    String id;

    public ResultadoCarteira getResultado() {
        return resultado;
    }

    public String getResultadoString() {
        return resultado.toString();
    }

    public String getId() {
        return id;
    }

    public ResultadoCarteira() {
    }
}
