package com.alineavila.jornadamilhas.domain.destino;

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
    byte[] foto;
    @NotBlank
    String nome;
    @NotNull
    BigDecimal preco;

    public Destino(MultipartFile foto, String nome, BigDecimal preco) throws IOException {
        this.setFoto(foto.getBytes());
        this.setNome(nome);
        this.setPreco(preco);
    }

    public void atualizar(MultipartFile foto, String nome, BigDecimal preco) throws IOException {
        if (!foto.isEmpty()) {
            this.foto = foto.getBytes();
        }
        if (nome != null) {
            this.nome = nome;
        }
        if (preco != null) {
            this.preco = preco;
        }
    }
}
