package br.com.zupacademy.ifzup.proposta.cartao;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Entity
public class Bloqueio {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String id;
    @NotNull
    private LocalDateTime bloqueadoEm;
    private String sistemaResponsavel;
    @NotNull
    private boolean ativo = false;
    @ManyToOne(cascade = CascadeType.MERGE)
    private Cartao cartao;
    private String userAgent;
    private String ip;

    @Deprecated
    public Bloqueio() {
    }

    public Bloqueio(String userAgent, String ip, Cartao cartao, boolean ativo) {
        this.bloqueadoEm = LocalDateTime.now();
        this.userAgent = userAgent;
        this.ativo = ativo;
        this.cartao = cartao;
        this.ip = ip;
    }

    public Bloqueio(String userAgent, String sistemaResponsavel, boolean ativo) {
        this.userAgent = userAgent;
        this.ativo = ativo;
        this.sistemaResponsavel = sistemaResponsavel;
    }

    public boolean isAtivo() {
        return ativo;
    }

    public void setSistemaResponsavel(String sistemaResponsavel) {
        this.sistemaResponsavel = sistemaResponsavel;
    }

    public String getSistemaResponsavel() {
        return sistemaResponsavel;
    }

    public void setAtivo(boolean ativo) {
        this.ativo = ativo;
    }
}
