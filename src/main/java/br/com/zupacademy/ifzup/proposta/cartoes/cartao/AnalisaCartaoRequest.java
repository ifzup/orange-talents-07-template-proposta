package br.com.zupacademy.ifzup.proposta.cartoes.cartao;

import br.com.zupacademy.ifzup.proposta.proposta.Proposta;

public class AnalisaCartaoRequest {

    private String documento;
    private String nome;
    private String idProposta;

    @Deprecated
    public AnalisaCartaoRequest() {
    }

    public AnalisaCartaoRequest(String documento, String nome, String idProposta) {
        this.documento = documento;
        this.nome = nome;
        this.idProposta = idProposta;
    }

    public AnalisaCartaoRequest(Proposta proposta) {
        this.documento = proposta.getDocumento();
        this.nome = proposta.getNome();
        this.idProposta = proposta.getId().toString();
    }

    public String getDocumento() {
        return documento;
    }

    public String getNome() {
        return nome;
    }

    public String getIdProposta() {
        return idProposta;
    }
}
