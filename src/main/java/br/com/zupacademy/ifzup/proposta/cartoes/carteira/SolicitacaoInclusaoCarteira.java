package br.com.zupacademy.ifzup.proposta.cartoes.carteira;

import javax.validation.constraints.NotBlank;

public class SolicitacaoInclusaoCarteira {

    @NotBlank
    String email;
    String carteira;

    public String getEmail() {
        return email;
    }

    public String getCarteira() {
        return carteira;
    }

    public SolicitacaoInclusaoCarteira(@NotBlank String email, String carteira) {
        this.email = email;
        this.carteira = carteira;
    }
}
