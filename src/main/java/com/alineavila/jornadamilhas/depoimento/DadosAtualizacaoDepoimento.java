package com.alineavila.jornadamilhas.depoimento;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record DadosAtualizacaoDepoimento(
        @NotNull
        Long id,
        String foto,
        String depoimento,
        String autor
) {


}
