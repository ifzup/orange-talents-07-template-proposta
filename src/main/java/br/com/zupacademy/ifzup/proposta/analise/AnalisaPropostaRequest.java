package br.com.zupacademy.ifzup.proposta.analise;

public class AnalisaPropostaRequest {
    private String documento;
    private String nome;
    private Long idProposta;

    @Deprecated
    public AnalisaPropostaRequest() {
    }

    public AnalisaPropostaRequest(String documento, String nome, Long idProposta) {
        this.documento = documento;
        this.nome = nome;
        this.idProposta = idProposta;
    }

    public String getDocumento() {
        return documento;
    }

    public String getNome() {
        return nome;
    }

    public Long getIdProposta() {
        return idProposta;
    }
}
