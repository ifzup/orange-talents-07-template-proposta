package br.com.zupacademy.ifzup.proposta.cartao;

import javax.persistence.*;
import java.time.LocalDateTime;


//revisar carteira depois
@Entity
public class Carteira {

    @Id
    private String id;
    private String email;
    private LocalDateTime associadaEm;
    @Column(unique = true)
    private String emissor;
    @ManyToOne(cascade = CascadeType.MERGE)
    Cartao cartao;

    public Carteira(String id, String email, LocalDateTime associadaEm, String emissor) {
        this.id = id;
        this.email = email;
        this.associadaEm = associadaEm;
        this.emissor = emissor;
    }

    public Carteira(SolicitacaoInclusaoCarteira solicitacao,
                    ResultadoCarteira resultado,
                    Cartao cartao) {
        this.id = resultado.getId();
        this.emissor = solicitacao.getCarteira();
        this.associadaEm = LocalDateTime.now();
        this.email = solicitacao.getEmail();
        this.cartao = cartao;

    }

    public String getId() {
        return id;
    }
}
