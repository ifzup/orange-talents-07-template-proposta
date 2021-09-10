package br.com.zupacademy.ifzup.proposta.cartoes.aviso;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import java.util.Objects;

public class AvisoViagemResult {

    @Enumerated(EnumType.STRING)
    AvisoViagemResultEnum resultado;

    public AvisoViagemResult(AvisoViagemResultEnum resultado) {
        this.resultado = resultado;
    }

    public AvisoViagemResultEnum getResultado() {
        return resultado;
    }

    @Override
    public boolean equals(Object o) {
        if (this.resultado == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AvisoViagemResult that = (AvisoViagemResult) o;
        return resultado == that.resultado;
    }

    @Override
    public int hashCode() {
        return Objects.hash(resultado);
    }

    public AvisoViagemResult() {
    }
}
