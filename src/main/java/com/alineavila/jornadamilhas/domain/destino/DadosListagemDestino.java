package com.alineavila.jornadamilhas.domain.destino;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;

public record DadosListagemDestino(

        Long id,
        byte[] foto,
        String nome,
        BigDecimal preco
) {

    public DadosListagemDestino(Destino destino) {
        this(destino.getId(), destino.getFoto(), destino.getNome(), destino.getPreco());
    }
}
