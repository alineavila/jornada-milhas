package com.alineavila.jornadamilhas.domain.destino;

import com.alineavila.jornadamilhas.infra.IntegraOpenAi;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.math.BigDecimal;

@Entity
@Table(name = "destinos")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

public class Destino {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    @NotNull
    byte[] foto1;
    @NotNull
    byte[] foto2;
    @NotBlank
    String nome;
    @NotNull
    BigDecimal preco;
    @NotBlank
    String meta;
    @Column(name="descricao")
    String descricao;


    public Destino(MultipartFile foto1, MultipartFile foto2, String nome, BigDecimal preco, String meta) throws IOException {
        this.setFoto1(foto1.getBytes());
        this.setFoto2(foto2.getBytes());
        this.setNome(nome);
        this.setPreco(preco);
        this.setMeta(meta);
        this.setDescricao(this.adicionarDescricaoUsandoOpenAi(nome));
    }

    private String adicionarDescricaoUsandoOpenAi(String nomeDestino) {
        IntegraOpenAi chatGpt = new IntegraOpenAi();
        return chatGpt.realizaOPrompt(nomeDestino);
    }

    public void atualizar(MultipartFile foto1, MultipartFile foto2, String nome, BigDecimal preco) throws IOException {
        if (!foto1.isEmpty()) {
            this.foto1 = foto1.getBytes();
        }

        if (!foto2.isEmpty()) {
            this.foto1 = foto2.getBytes();
        }
        if (nome != null) {
            this.nome = nome;
        }
        if (preco != null) {
            this.preco = preco;
        }
    }

    public boolean isEmpty() {
        return (this.getId() == null || this.getId() == 0 );
    }
}
