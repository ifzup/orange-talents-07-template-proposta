package br.com.zupacademy.ifzup.proposta.biometria;

import br.com.zupacademy.ifzup.proposta.cartoes.cartao.Cartao;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Entity
public class Biometria {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    String fingerPrint;

    @OneToOne
    @NotNull
    Cartao cartao;

    public Biometria(BiometriaRequest request, Cartao cartao) {
        this.fingerPrint = request.hash();
        this.cartao = cartao;
    }

    public Long getId() {
        return id;
    }
}
