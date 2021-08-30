package br.com.zupacademy.ifzup.proposta.proposta;

import br.com.zupacademy.ifzup.proposta.analise.Status;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface PropostaRepository extends JpaRepository<Proposta, Long> {


    boolean existsByDocumento(String documento);

    public List<Proposta> findByStatus(Status status);

    public List<Proposta> findByStatusAndCartaoIsNull(Status status);
}
