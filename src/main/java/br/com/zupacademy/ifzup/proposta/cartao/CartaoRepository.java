package br.com.zupacademy.ifzup.proposta.cartao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

public interface CartaoRepository extends JpaRepository<Cartao, Long> {


    Cartao findByNumeroCartao(String numeroCartao);
}
