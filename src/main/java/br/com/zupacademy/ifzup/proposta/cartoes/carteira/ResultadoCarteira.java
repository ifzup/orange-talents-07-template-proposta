package br.com.zupacademy.ifzup.proposta.cartoes.carteira;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;

public class ResultadoCarteira {
    @Enumerated(EnumType.STRING)
    ResultadoCarteiraEnum resultado;
    String id;



    public String getResultadoString() {
        return resultado.toString();
    }

    public ResultadoCarteiraEnum getResultado() {
        return resultado;
    }

    public String getId() {
        return id;
    }

    public ResultadoCarteira() {
    }
}
