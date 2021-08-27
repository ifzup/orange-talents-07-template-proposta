package br.com.zupacademy.ifzup.proposta.cartao;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.time.LocalDateTime;


@Entity
public class Cartao {

    private String idCartao;

    private LocalDateTime emitidoEm;

    private String titular;
}

