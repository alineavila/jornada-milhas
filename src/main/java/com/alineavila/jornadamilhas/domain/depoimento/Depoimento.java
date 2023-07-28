package com.alineavila.jornadamilhas.depoimento;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.sql.Blob;

@Entity
@Table(name="depoimentos")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Depoimento {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    @NotNull
    byte[] foto;
    @NotBlank
    @Lob
    String depoimento;
    @NotBlank
    String autor;

    public Depoimento(DadosCadastroDepoimento dados) {
        this.foto = dados.foto();
        this.depoimento = dados.depoimento();
        this.autor = dados.autor();
    }

    public Depoimento(MultipartFile foto, String depoimento, String autor) throws IOException {
        this.setFoto(foto.getBytes());
        this.setDepoimento(depoimento);
        this.setAutor(autor);
    }

    public void atualizar(MultipartFile foto, String depoimento, String autor) throws IOException {
        if (!foto.isEmpty()){
            this.foto = foto.getBytes();
        }
        if (depoimento != null){
            this.depoimento = depoimento;
        }
        if (autor != null){
            this.autor = autor;
        }
    }
}
