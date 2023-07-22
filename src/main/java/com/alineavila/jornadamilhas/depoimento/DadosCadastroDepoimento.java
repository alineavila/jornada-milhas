package com.alineavila.jornadamilhas.depoimento;

import jakarta.validation.constraints.NotBlank;

public record DadosCadastroDepoimento(

    @NotBlank
    String foto,
    @NotBlank
    String depoimento,
    @NotBlank
    String autor ){
}
