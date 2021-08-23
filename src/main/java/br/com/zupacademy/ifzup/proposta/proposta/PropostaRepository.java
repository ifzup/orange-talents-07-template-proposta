package br.com.zupacademy.ifzup.proposta.proposta;

import org.springframework.data.repository.CrudRepository;

public interface PropostaRepository extends CrudRepository<Proposta, Long> {


    boolean existsByDocumento(String documento);
}
