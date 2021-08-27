package br.com.zupacademy.ifzup.proposta.analise;

import br.com.zupacademy.ifzup.proposta.proposta.Proposta;

public class AnalisaPropostaRequest {
    private String documento;
    private String nome;
    private String idProposta;

    @Deprecated
    public AnalisaPropostaRequest() {
    }

    public AnalisaPropostaRequest(String documento, String nome, String idProposta) {
    }

    public AnalisaPropostaRequest(Proposta proposta) {
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

    public Long getIdPropostaLong(){return Long.valueOf(idProposta);}
}
