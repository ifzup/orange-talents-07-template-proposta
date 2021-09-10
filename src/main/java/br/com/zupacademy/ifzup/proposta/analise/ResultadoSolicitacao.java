package br.com.zupacademy.ifzup.proposta.analise;

public enum ResultadoSolicitacao {

    COM_RESTRICAO(Status.NAO_ELEGIVEL),
    SEM_RESTRICAO(Status.ELEGIVEL);

    private final Status status;

    ResultadoSolicitacao(Status status) {
        this.status = status;
    }

    public Status getStatus() {
        return status;
    }
}
