package br.com.zupacademy.ifzup.proposta.cartao;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
public class Parcela {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Integer quantidade;
    private BigDecimal valor;
    @ManyToOne
    Cartao cartao;

    public Parcela(ParcelaRequest request){
        this.id = Long.valueOf(request.getId());
        this.quantidade = request.getQuantidade();
        this.valor = BigDecimal.valueOf(request.getValor());
    }

    @Deprecated
    public Parcela() {
    }

    public Parcela(String id, Integer quantidade, BigDecimal valor) {
        this.id = Long.valueOf(id);
        this.quantidade = quantidade;
        this.valor = valor;
    }
}
