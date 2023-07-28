package com.alineavila.jornadamilhas.depoimento;

import jakarta.validation.constraints.NotBlank;
import lombok.EqualsAndHashCode;

import java.sql.Blob;

public record DadosCadastroDepoimento(

    @NotBlank
    byte[] foto,
    @NotBlank
    String depoimento,
    @NotBlank
    String autor ) {
}
