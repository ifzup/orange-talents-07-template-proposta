package br.com.zupacademy.ifzup.proposta.cartao;

import java.time.LocalDateTime;
import java.util.List;

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

    @Deprecated
    public AnalisaCartaoResponse() { }

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
}