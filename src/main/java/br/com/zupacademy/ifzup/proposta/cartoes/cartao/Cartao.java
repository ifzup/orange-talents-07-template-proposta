package br.com.zupacademy.ifzup.proposta.cartoes.cartao;

import br.com.zupacademy.ifzup.proposta.cartoes.aviso.Aviso;
import br.com.zupacademy.ifzup.proposta.cartoes.bloqueio.Bloqueio;
import br.com.zupacademy.ifzup.proposta.cartoes.carteira.Carteira;
import br.com.zupacademy.ifzup.proposta.cartoes.parcela.Parcela;
import br.com.zupacademy.ifzup.proposta.cartoes.renegociacao.Renegociacao;
import br.com.zupacademy.ifzup.proposta.cartoes.vencimento.Vencimento;
import br.com.zupacademy.ifzup.proposta.proposta.Proposta;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;


@Entity
public class Cartao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idCartao;

    private String numeroCartao;

    private LocalDateTime emitidoEm;

    private String titular;

    @OneToMany(mappedBy = "cartao")
    private List<Bloqueio> bloqueios = new ArrayList<>();
    @OneToMany(mappedBy = "cartao")
    private List<Aviso> avisos = new ArrayList<>();
    @OneToMany(mappedBy = "cartao")
    private List<Carteira> carteiras = new ArrayList<>();
    @OneToMany(mappedBy = "cartao")
    private List<Parcela> parcelas = new ArrayList<>();
    private BigDecimal limite;
    @Embedded
    private Renegociacao renegociacao;
    @Embedded
    private Vencimento vencimento;
    @OneToOne(mappedBy = "cartao")
    private Proposta proposta;


    @Deprecated
    public Cartao() {
    }

    public Cartao(String id, LocalDateTime emitidoEm, String titular, List<Bloqueio> bloqueios, List<Aviso> avisos, List<Carteira> carteiras, List<Parcela> parcelas, BigDecimal limite, Renegociacao renegociacao, Vencimento vencimento, Proposta proposta) {
        this.numeroCartao = id;
        this.emitidoEm = emitidoEm;
        this.titular = titular;
        this.bloqueios = bloqueios;
        this.avisos = avisos;
        this.carteiras = carteiras;
        this.parcelas = parcelas;
        this.limite = limite;
        this.renegociacao = renegociacao;
        this.vencimento = vencimento;
        this.proposta = proposta;
    }

    public static Cartao procuraCartaoPorNumero(EntityManager manager, String numeroCartao) {
        return manager.find(Cartao.class, numeroCartao);
    }

    public String getNumeroCartao() {
        return numeroCartao;
    }

    public LocalDateTime getEmitidoEm() {
        return emitidoEm;
    }

    public String getTitular() {
        return titular;
    }

    public List<Bloqueio> getBloqueios() {
        return bloqueios;
    }

    public List<Aviso> getAvisos() {
        return avisos;
    }

    public List<Carteira> getCarteiras() {
        return carteiras;
    }

    public List<Parcela> getParcelas() {
        return parcelas;
    }

    public BigDecimal getLimite() {
        return limite;
    }

    public Renegociacao getRenegociacao() {
        return renegociacao;
    }

    public Vencimento getVencimento() {
        return vencimento;
    }

    public Long getIdCartao() {
        return idCartao;
    }

    public void adcionaAviso(Aviso aviso) {
        this.avisos.add(aviso);
    }
}
