package br.com.zupacademy.ifzup.proposta.cartoes.vencimento;

import javax.persistence.Embeddable;
import java.time.LocalDateTime;

@Embeddable
public class Vencimento {

    private String idVencimento;
    private int dia;
    private LocalDateTime dataDeCriacaoVencimento;

    public Vencimento(String id, int dia, LocalDateTime dataDeCriacao) {
        this.idVencimento = id;
        this.dia = dia;
        this.dataDeCriacaoVencimento = dataDeCriacao;
    }

    @Deprecated
    public Vencimento() {
    }
}
