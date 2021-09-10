package br.com.zupacademy.ifzup.proposta.cartao;

import javax.validation.constraints.Future;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

public class AvisoRequest {
    @NotNull
    @Future
    private LocalDate validoAte;
    @NotBlank
    private String destino;

    public AvisoRequest(LocalDate validoAte, String destino) {
        this.validoAte = validoAte;
        this.destino = destino;
    }


    public Aviso paraAviso(){
        return new Aviso(this.validoAte, this.destino);
    }

    public LocalDate getValidoAte() {
        return validoAte;
    }

    public String getDestino() {
        return destino;
    }

    public void setValidoAte(LocalDate validoAte) {
        this.validoAte = validoAte;
    }

    public void setDestino(String destino) {
        this.destino = destino;
    }
}
