package br.com.zupacademy.ifzup.proposta.cartao;

import javax.persistence.*;
import javax.validation.constraints.Future;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.Instant;
import java.time.LocalDate;

@Entity
public class Aviso {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotNull
    @Future
    private LocalDate validoAte;
    @NotBlank
    private String destino;
    private Instant instanteDoAviso;
    @ManyToOne(cascade = CascadeType.MERGE)
    private Cartao cartao;
    private String ip;
    private String userAgent;

    public Aviso(LocalDate validoAte, String destino) {
        this.validoAte = validoAte;
        this.destino = destino;
    }

    public Aviso(AvisoRequest avisoRequest, Cartao cartao, String userAgent, String ip){
        this.validoAte = avisoRequest.getValidoAte();
        this.destino = avisoRequest.getDestino();
        this.cartao = cartao;
        this.instanteDoAviso = Instant.now();
        this.userAgent = userAgent;
        this.ip = ip;
    }

    @Deprecated
    public Aviso(){}
}
