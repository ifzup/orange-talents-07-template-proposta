package br.com.zupacademy.ifzup.proposta.cartoes.carteira;

import br.com.zupacademy.ifzup.proposta.cartoes.cartao.Cartao;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.time.LocalDateTime;

@Entity
public class Carteira {

    @Id
    private String id;
    @NotBlank
    private String email;
    private LocalDateTime associadaEm;
    @Column(unique = true)
    private String emissor;
    @ManyToOne(cascade = CascadeType.MERGE)
    Cartao cartao;

    @Enumerated(EnumType.STRING)
    private CarteiraEnum carteirasEnum;

    public Carteira(String id, String email, LocalDateTime associadaEm, String emissor) {
        this.id = id;
        this.email = email;
        this.associadaEm = associadaEm;
        this.emissor = emissor;
    }

    public Carteira(CarteiraEnum carteiraEnum,
            SolicitacaoInclusaoCarteira solicitacao,
                    ResultadoCarteira resultado,
                    Cartao cartao) {
        this.carteirasEnum = carteiraEnum;
        this.id = resultado.getId();
        this.emissor = solicitacao.getCarteira();
        this.associadaEm = LocalDateTime.now();
        this.email = solicitacao.getEmail();
        this.cartao = cartao;

    }

    @Deprecated
    public Carteira() {
    }

    public String getId() {
        return id;
    }
}
