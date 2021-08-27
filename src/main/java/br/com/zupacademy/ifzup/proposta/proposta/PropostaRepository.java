package br.com.zupacademy.ifzup.proposta.proposta;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

public interface PropostaRepository extends JpaRepository<Proposta, Long> {


    boolean existsByDocumento(String documento);
}
