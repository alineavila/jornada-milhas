package com.alineavila.jornadamilhas.domain.destino;

import java.math.BigDecimal;

public record DadosDetalhamentoDestino(byte[] foto,
                                       String nome,
                                       BigDecimal preco) {

    public DadosDetalhamentoDestino(Destino destino){
        this(destino.getFoto(), destino.getNome(), destino.getPreco());
    }
}
