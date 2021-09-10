package br.com.zupacademy.ifzup.proposta.cartoes.cartao;

import org.springframework.data.jpa.repository.JpaRepository;

public interface CartaoRepository extends JpaRepository<Cartao, Long> {

    Cartao findByNumeroCartao(String numeroCartao);
}
