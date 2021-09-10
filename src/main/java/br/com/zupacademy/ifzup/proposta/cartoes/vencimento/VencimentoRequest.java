package br.com.zupacademy.ifzup.proposta.cartoes.vencimento;

import java.time.LocalDateTime;

public class VencimentoRequest {
    private String id;
    private int dia;
    private LocalDateTime dataDeCriacao;

    public VencimentoRequest(String id, int dia, LocalDateTime dataDeCriacao) {
        this.id = id;
        this.dia = dia;
        this.dataDeCriacao = dataDeCriacao;
    }

    public Vencimento paraVencimento() {
        return new Vencimento(id, dia, dataDeCriacao);
    }

    public String getId() {
        return id;
    }

    public int getDia() {
        return dia;
    }

    public LocalDateTime getDataDeCriacao() {
        return dataDeCriacao;
    }
}
