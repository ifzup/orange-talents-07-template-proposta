package br.com.zupacademy.ifzup.proposta.cartoes.renegociacao;

import javax.persistence.Embeddable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Embeddable
public class Renegociacao {
    private String idRenegociacao;
    private Integer quantidade;
    private BigDecimal valor;
    private LocalDateTime dataDeCriacaoRenegociacao;

    public Renegociacao(String id, Integer quantidade, BigDecimal valor, LocalDateTime dataDeCriacaoRenegociacao) {
        this.idRenegociacao = id;
        this.quantidade = quantidade;
        this.valor = valor;
        this.dataDeCriacaoRenegociacao = dataDeCriacaoRenegociacao;
    }

    @Deprecated
    public Renegociacao() {

    }
}
