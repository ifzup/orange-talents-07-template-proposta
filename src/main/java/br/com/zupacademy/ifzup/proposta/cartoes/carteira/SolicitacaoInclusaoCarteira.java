package br.com.zupacademy.ifzup.proposta.cartoes.carteira;

public class SolicitacaoInclusaoCarteira {
    String email;
    String carteira;

    public String getEmail() {
        return email;
    }

    public String getCarteira() {
        return carteira;
    }

    public SolicitacaoInclusaoCarteira(String email, String carteira) {
        this.email = email;
        this.carteira = carteira;
    }
}
