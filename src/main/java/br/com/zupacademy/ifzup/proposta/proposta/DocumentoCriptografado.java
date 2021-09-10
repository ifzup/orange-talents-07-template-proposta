package br.com.zupacademy.ifzup.proposta.proposta;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.validation.Valid;

public class DocumentoCriptografado {

    private String documento;

    @Valid
    public DocumentoCriptografado(String documento) {
        this.documento = documento;
    }

    public String hash(){
        return new BCryptPasswordEncoder().encode(documento);
    }
}
