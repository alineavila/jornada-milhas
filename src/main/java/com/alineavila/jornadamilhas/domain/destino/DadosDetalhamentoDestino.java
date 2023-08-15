package com.alineavila.jornadamilhas.domain.destino;

import java.math.BigDecimal;

public record DadosDetalhamentoDestino(
        byte[] foto1,
        byte[] foto2,
        String nome,

        BigDecimal preco,
        String meta,
        String descricao) {

    public DadosDetalhamentoDestino(Destino destino) {
        this(destino.getFoto1(), destino.getFoto2(), destino.getNome(), destino.getPreco(), destino.getMeta(), destino.getDescricao());
    }
}
