package com.alineavila.jornadamilhas.domain.depoimento;

public record DadosDetalhamentoDepoimento(
        byte[] foto,
        String depoimento,
        String autor) {

    public DadosDetalhamentoDepoimento(Depoimento depoimento) {
        this(depoimento.getFoto(), depoimento.getDepoimento(), depoimento.getAutor());
    }

}
