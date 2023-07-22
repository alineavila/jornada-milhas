package com.alineavila.jornadamilhas.depoimento;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name="depoimentos")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Depoimento {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    @NotBlank
    String foto;
    @NotBlank
    String depoimento;
    @NotBlank
    String autor;

    public Depoimento(DadosCadastroDepoimento dados) {
        this.foto = dados.foto();
        this.depoimento = dados.depoimento();
        this.autor = dados.autor();
    }

    public void atualizar(DadosAtualizacaoDepoimento dados) {
        if (dados.foto() != null){
            this.foto = dados.foto();
        }
        if (dados.depoimento() != null){
            this.depoimento = dados.depoimento();
        }
        if (dados.autor() != null){
            this.autor = dados.autor();
        }
    }
}
