package br.com.zupacademy.ifzup.proposta.cartoes.renegociacao;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class RenegociacaoRequest {
    private String id;
    private Integer quantidade;
    private Integer valor;
    private LocalDateTime dataDeCriacao;

    public RenegociacaoRequest(String id, Integer quantidade, Integer valor, LocalDateTime dataDeCriacao) {
        this.id = id;
        this.quantidade = quantidade;
        this.valor = valor;
        this.dataDeCriacao = dataDeCriacao;
    }

    public Renegociacao paraRenegociacao() {
        return new Renegociacao(id, quantidade, BigDecimal.valueOf(valor), dataDeCriacao);
    }

    public String getId() {
        return id;
    }
}
