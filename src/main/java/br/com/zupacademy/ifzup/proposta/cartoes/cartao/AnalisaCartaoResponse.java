package br.com.zupacademy.ifzup.proposta.cartoes.cartao;

import br.com.zupacademy.ifzup.proposta.cartoes.aviso.Aviso;
import br.com.zupacademy.ifzup.proposta.cartoes.aviso.AvisoRequest;
import br.com.zupacademy.ifzup.proposta.cartoes.bloqueio.Bloqueio;
import br.com.zupacademy.ifzup.proposta.cartoes.bloqueio.BloqueioRequest;
import br.com.zupacademy.ifzup.proposta.cartoes.carteira.Carteira;
import br.com.zupacademy.ifzup.proposta.cartoes.carteira.CarteiraRequest;
import br.com.zupacademy.ifzup.proposta.cartoes.parcela.Parcela;
import br.com.zupacademy.ifzup.proposta.cartoes.parcela.ParcelaRequest;
import br.com.zupacademy.ifzup.proposta.cartoes.renegociacao.Renegociacao;
import br.com.zupacademy.ifzup.proposta.cartoes.renegociacao.RenegociacaoRequest;
import br.com.zupacademy.ifzup.proposta.cartoes.vencimento.Vencimento;
import br.com.zupacademy.ifzup.proposta.cartoes.vencimento.VencimentoRequest;
import br.com.zupacademy.ifzup.proposta.proposta.Proposta;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

public class AnalisaCartaoResponse {

    private String id;
    private LocalDateTime emitidoEm;
    private String titular;
    private List<BloqueioRequest> bloqueios;
    private List<AvisoRequest> avisos;
    private List<CarteiraRequest> carteiras;
    private List<ParcelaRequest> parcelas;
    private Integer limite;
    private RenegociacaoRequest renegociacao;
    private VencimentoRequest vencimento;
    private String idProposta;


    public AnalisaCartaoResponse(String id,
                                 LocalDateTime emitidoEm,
                                 String titular,
                                 List<BloqueioRequest> bloqueios,
                                 List<AvisoRequest> avisos,
                                 List<CarteiraRequest> carteiras,
                                 List<ParcelaRequest> parcelas,
                                 Integer limite,
                                 RenegociacaoRequest renegociacao,
                                 VencimentoRequest vencimento,
                                 String idProposta) {
        this.id = id;
        this.emitidoEm = emitidoEm;
        this.titular = titular;
        this.bloqueios = bloqueios;
        this.avisos = avisos;
        this.carteiras = carteiras;
        this.parcelas = parcelas;
        this.limite = limite;
        this.renegociacao = renegociacao;
        this.vencimento = vencimento;
        this.idProposta = idProposta;
    }

    public Cartao paraCartao(Proposta proposta) {
        List<Bloqueio> bloqueios = this.bloqueios.stream().map(BloqueioRequest::paraBloqueio).collect(Collectors.toList());
        List<Aviso> avisos = this.avisos.stream().map(AvisoRequest::paraAviso).collect(Collectors.toList());
        List<Carteira> carteiras = this.carteiras.stream().map(CarteiraRequest::paraCarteira).collect(Collectors.toList());
        List<Parcela> parcelas = this.parcelas.stream().map(ParcelaRequest::paraParcela).collect(Collectors.toList());
        return new Cartao(id, emitidoEm, titular, bloqueios, avisos, carteiras, parcelas, new BigDecimal(limite.toString()), new Renegociacao(), new Vencimento(), proposta);
    }

    @Deprecated
    public AnalisaCartaoResponse() {
    }

    public String getId() {
        return id;
    }

    public LocalDateTime getEmitidoEm() {
        return emitidoEm;
    }

    public String getTitular() {
        return titular;
    }

    public List<CarteiraRequest> getCarteiras() {
        return carteiras;
    }

    public List<ParcelaRequest> getParcelas() {
        return parcelas;
    }

    public Integer getLimite() {
        return limite;
    }

    public String getIdProposta() {
        return idProposta;
    }

    public List<BloqueioRequest> getBloqueios() {
        return bloqueios;
    }

    public List<AvisoRequest> getAvisos() {
        return avisos;
    }

    public RenegociacaoRequest getRenegociacao() {
        return renegociacao;
    }

    public VencimentoRequest getVencimento() {
        return vencimento;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setEmitidoEm(LocalDateTime emitidoEm) {
        this.emitidoEm = emitidoEm;
    }

    public void setTitular(String titular) {
        this.titular = titular;
    }

    public void setBloqueios(List<BloqueioRequest> bloqueios) {
        this.bloqueios = bloqueios;
    }

    public void setAvisos(List<AvisoRequest> avisos) {
        this.avisos = avisos;
    }

    public void setCarteiras(List<CarteiraRequest> carteiras) {
        this.carteiras = carteiras;
    }

    public void setParcelas(List<ParcelaRequest> parcelas) {
        this.parcelas = parcelas;
    }

    public void setLimite(Integer limite) {
        this.limite = limite;
    }

    public void setRenegociacao(RenegociacaoRequest renegociacao) {
        this.renegociacao = renegociacao;
    }

    public void setVencimento(VencimentoRequest vencimento) {
        this.vencimento = vencimento;
    }

    public void setIdProposta(String idProposta) {
        this.idProposta = idProposta;
    }

    @Override
    public String toString() {
        return "AnalisaCartaoResponse{" +
                "id='" + id + '\'' +
                ", emitidoEm=" + emitidoEm +
                ", titular='" + titular + '\'' +
                ", bloqueios=" + bloqueios +
                ", avisos=" + avisos +
                ", carteiras=" + carteiras +
                ", parcelas=" + parcelas +
                ", limite=" + limite +
                ", renegociacao=" + renegociacao +
                ", vencimento=" + vencimento +
                ", idProposta='" + idProposta + '\'' +
                '}';
    }
}