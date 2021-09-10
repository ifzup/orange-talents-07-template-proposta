package br.com.zupacademy.ifzup.proposta.cartoes.bloqueio;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;

public class ResultadoBloqueio {
    @Enumerated(EnumType.STRING)
    BloqueioEnum bloqueioEnum;

    public BloqueioEnum getResultadoBloqueio() {
        return bloqueioEnum;
    }

    public ResultadoBloqueio(BloqueioEnum bloqueioEnum) {
        this.bloqueioEnum = bloqueioEnum;
    }

    public void setResultadoBloqueio(BloqueioEnum bloqueioEnum) {
        this.bloqueioEnum = bloqueioEnum;
    }

    @Deprecated
    public ResultadoBloqueio() {
    }
}
