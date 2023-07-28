package com.alineavila.jornadamilhas.domain.depoimento;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.sql.Blob;

public record DadosAtualizacaoDepoimento(
        @NotNull
        Long id,
        byte[] foto,
        String depoimento,
        String autor
) {


}
