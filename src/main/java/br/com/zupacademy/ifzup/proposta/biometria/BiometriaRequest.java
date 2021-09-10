package br.com.zupacademy.ifzup.proposta.biometria;

import javax.validation.constraints.NotBlank;
import java.util.Base64;

public class BiometriaRequest {

    @NotBlank
    String fingerPrint;

    public String hash() {
        return Base64.getEncoder().encodeToString(this.fingerPrint.getBytes());
    }

    public String getFingerPrint() {
        return fingerPrint;
    }
}
