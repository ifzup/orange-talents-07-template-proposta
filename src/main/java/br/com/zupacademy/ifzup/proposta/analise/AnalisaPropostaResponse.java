package br.com.zupacademy.ifzup.proposta.analise;

public class AnalisaPropostaResponse {

    private String documento;
    private String nome;
    private String idProposta;

    private ResultadoSolicitacao resultadoSolicitacao;

    public String getDocumento() {
        return documento;
    }

    public String getNome() {
        return nome;
    }

    public String getIdProposta() {
        return idProposta;
    }

    public ResultadoSolicitacao getResultatadoSolicitacao() {
        return resultadoSolicitacao;
    }

    public Status status(){
        return resultadoSolicitacao.getStatus();
    }
}
