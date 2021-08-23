package br.com.zupacademy.ifzup.proposta.proposta;

import br.com.zupacademy.ifzup.proposta.validator.CpfOuCnpj;
import br.com.zupacademy.ifzup.proposta.validator.UniqueValue;

import javax.persistence.Column;
import javax.persistence.EntityManager;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.math.BigDecimal;

public class PropostaRequest {


    @CpfOuCnpj
    @NotBlank
    //@UniqueValue(domainClass = Proposta.class, fieldName = "documento")
    private String documento;

    @Email
    @NotBlank
    private String email;

    @NotBlank
    private String nome;

    @NotBlank
    private String endereco;

    @NotNull
    @Positive
    private BigDecimal salario;

    public PropostaRequest(String documento, String email, String nome, String endereco, BigDecimal salario) {
        this.documento = documento;
        this.email = email;
        this.nome = nome;
        this.endereco = endereco;
        this.salario = salario;
    }


    public Proposta converter() {
        return new Proposta(documento,email, nome, endereco, salario);
    }

    public String getDocumento() {
        return documento;
    }
}
