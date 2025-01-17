package br.com.zupacademy.ifzup.proposta.proposta;

import br.com.zupacademy.ifzup.proposta.analise.Status;
import br.com.zupacademy.ifzup.proposta.cartoes.cartao.Cartao;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.math.BigDecimal;

@Entity
public class Proposta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    @NotBlank
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

    @Enumerated(EnumType.STRING)
    private Status status;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn
    private Cartao cartao;

    @Deprecated
    public Proposta() {
    }

    public Proposta(DocumentoCriptografado documento, String email, String nome, String endereco, BigDecimal salario) {
        this.documento = documento.hash();
        this.email = email;
        this.nome = nome;
        this.endereco = endereco;
        this.salario = salario;
    }

    public Proposta(DocumentoCriptografado documento, String email, String nome, String endereco, BigDecimal salario, Cartao cartao) {
        this.documento = documento.hash();
        this.email = email;
        this.nome = nome;
        this.endereco = endereco;
        this.salario = salario;
        this.cartao = cartao;
    }

    public Long getId() {
        return this.id;
    }

    public String getDocumento() {
        return documento;
    }

    public String getEmail() {
        return email;
    }

    public String getNome() {
        return nome;
    }

    public String getEndereco() {
        return endereco;
    }

    public BigDecimal getSalario() {
        return salario;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public void associarCartao(Cartao cartao) {
        this.cartao = cartao;
    }

    public Status getStatus() {
        return status;
    }

    public Cartao getCartao() {
        return cartao;
    }
}
