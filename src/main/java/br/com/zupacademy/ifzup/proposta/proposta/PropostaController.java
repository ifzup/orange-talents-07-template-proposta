package br.com.zupacademy.ifzup.proposta.proposta;

import br.com.zupacademy.ifzup.proposta.analise.AnalisaPropostaRequest;
import br.com.zupacademy.ifzup.proposta.analise.AnalisaPropostaResponse;
import br.com.zupacademy.ifzup.proposta.analise.AnalisaSolicitacaoClient;
import io.opentracing.Span;
import io.opentracing.Tracer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import javax.validation.Valid;
import java.net.URI;

@RestController
@RequestMapping("/api/propostas")
public class PropostaController {

    private final Tracer tracer;
    @PersistenceContext
    EntityManager manager;

    @Autowired
    PropostaRepository propostaRepository;

    @Autowired
    private AnalisaSolicitacaoClient analisaSolicitacaoClient;

    public PropostaController(Tracer tracer) {
        this.tracer = tracer;
    }

    @Transactional
    @PostMapping("/criar")
    public ResponseEntity<?> cadastrar(@RequestBody @Valid PropostaRequest request, UriComponentsBuilder uriBuilder) {
        Span activeSpan = tracer.activeSpan();
        activeSpan.setTag("user.email", "iagofaria777@gmail.com");
        activeSpan.log("Testando log: Proposta");



        Proposta proposta = request.converter();


        if (propostaRepository.existsByDocumento(request.getDocumento())) {
            return ResponseEntity.unprocessableEntity().body("Documento já utilizado");
        }

        propostaRepository.save(proposta);
        AnalisaPropostaResponse response = analisaSolicitacaoClient.consultaFeign(new AnalisaPropostaRequest(proposta));
        proposta.setStatus(response.getResultatadoSolicitacao().getStatus());

        URI enderecoCadastro = uriBuilder.path("/proposta/{id}").buildAndExpand(proposta.getId()).toUri();
        return ResponseEntity.created(enderecoCadastro).build();
    }

}
