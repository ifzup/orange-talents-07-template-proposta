package br.com.zupacademy.ifzup.proposta.cartao;

import javax.persistence.Embeddable;
import java.time.LocalDateTime;

@Embeddable
public class Vencimento {

    private String id;
    private int dia;
    private LocalDateTime dataDeCriacaoVencimento;

    public Vencimento(String id, int dia, LocalDateTime dataDeCriacao) {
        this.id = id;
        this.dia = dia;
        this.dataDeCriacaoVencimento = dataDeCriacao;
    }


    @Deprecated
    public Vencimento(){}
}
