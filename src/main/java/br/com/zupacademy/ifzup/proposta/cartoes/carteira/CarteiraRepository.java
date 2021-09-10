package br.com.zupacademy.ifzup.proposta.cartoes.carteira;

import org.springframework.data.jpa.repository.JpaRepository;

public interface CarteiraRepository extends JpaRepository<Carteira, String> {

    /*Carteira findByCarteirasEnum(CarteiraEnum paypal);*/

    Carteira findByCartaoIdCartao(Long idCartao);
}
