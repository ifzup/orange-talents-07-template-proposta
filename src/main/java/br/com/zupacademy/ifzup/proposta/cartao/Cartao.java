package br.com.zupacademy.ifzup.proposta.cartao;

import br.com.zupacademy.ifzup.proposta.proposta.Proposta;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;


@Entity
public class Cartao {

    @Id
    @Column(insertable = true, updatable = true)
    private String idCartao;

    private LocalDateTime emitidoEm;

    private String titular;

    @Embedded
    private List<Bloqueio> bloqueios;
    @Embedded
    private List<Aviso> avisos;
    @Embedded
    private List<Carteira> carteiras;
    @Embedded
    private List<Parcela> parcelas;
    private BigDecimal limite;
    @Embedded
    private Renegociacao renegociacao;
    @Embedded
    private Vencimento vencimento;
    @OneToOne
    private Proposta proposta;

    @Deprecated
    public Cartao() {
    }

    public Cartao(String idCartao, LocalDateTime emitidoEm, String titular, List<Bloqueio> bloqueios, List<Aviso> avisos, List<Carteira> carteiras, List<Parcela> parcelas, BigDecimal limite, Renegociacao renegociacao, Vencimento vencimento, Proposta proposta) {
        this.idCartao = idCartao;
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

    public Cartao(AnalisaCartaoResponse response, Proposta proposta) {
        this.idCartao = response.getId();
        this.emitidoEm = response.getEmitidoEm();
        this.titular = response.getTitular();
        this.bloqueios = response.getBloqueios().stream().map(BloqueioRequest::paraBloqueio).collect(Collectors.toList());
        this.avisos = response.getAvisos().stream().map(AvisoRequest::paraAviso).collect(Collectors.toList());
        this.carteiras = response.getCarteiras().stream().map(CarteiraRequest::paraCarteira).collect(Collectors.toList());
        this.parcelas = response.getParcelas().stream().map(ParcelaRequest::paraParcela).collect(Collectors.toList());
        this.limite = BigDecimal.valueOf(response.getLimite());
        if (response.getRenegociacao() == null) {
            this.renegociacao = null;
        } else {
            this.renegociacao = response.getRenegociacao().paraRenegociacao();
        }
        this.vencimento = response.getVencimento().paraVencimento();
        this.proposta = proposta;
    }
    public static Cartao procuraCartaoPorId(EntityManager manager, String id) {
        return manager.find(Cartao.class, id);
    }

    public String getIdCartao() {
        return idCartao;
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

    public Proposta getProposta() {
        return proposta;
    }
}
