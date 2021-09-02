package br.com.zupacademy.ifzup.proposta.cartao;

import br.com.zupacademy.ifzup.proposta.proposta.Proposta;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


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
/*
    public Cartao(CartaoRequest request, Proposta proposta) {
        this.numeroCartao = request.getId();
        this.emitidoEm = request.getEmitidoEm();
        this.titular = request.getTitular();
        this.bloqueios = request.getBloqueios()
                .stream()
                .map(BloqueioRequest::paraBloqueio)
                .collect(Collectors.toList());
        this.avisos = request.getAvisos()
                .stream().map(AvisoRequest::paraAviso).collect(Collectors.toList());
        this.carteiras = request.getCarteiras().stream().map(CarteiraRequest::paraCarteira).collect(Collectors.toList());
        this.parcelas = request.getParcelas().stream().map(ParcelaRequest::paraParcela).collect(Collectors.toList());
        this.limite = BigDecimal.valueOf(request.getLimite());
        if (request.getRenegociacao() == null) {
            this.renegociacao = null;
        } else {
            this.renegociacao = request.getRenegociacao().paraRenegociacao();
        }
        this.vencimento = request.getVencimento().paraVencimento();
        this.proposta = proposta;
    }*/

    public static Cartao procuraCartaoPorId(EntityManager manager, String id) {
        return manager.find(Cartao.class, id);
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
}
