package com.alineavila.jornadamilhas.domain.destino;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;

public record DadosListagemDestino(

        Long id,
        byte[] foto1,
        byte[] foto2,
        String nome,
        BigDecimal preco,
        String meta,
        String descricao
) {

    public DadosListagemDestino(Destino destino) {
        this(destino.getId(), destino.getFoto1(), destino.getFoto2(), destino.getNome(),
                destino.getPreco(), destino.getMeta(), destino.getDescricao());
    }
}
