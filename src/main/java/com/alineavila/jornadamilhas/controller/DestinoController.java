package com.alineavila.jornadamilhas.controller;

import com.alineavila.jornadamilhas.domain.destino.DadosDetalhamentoDestino;
import com.alineavila.jornadamilhas.domain.destino.DadosListagemDestino;
import com.alineavila.jornadamilhas.domain.destino.Destino;
import com.alineavila.jornadamilhas.domain.destino.DestinoRepository;
import jakarta.transaction.Transactional;
import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.util.UriComponentsBuilder;

import java.io.IOException;
import java.math.BigDecimal;

@RestController
@RequestMapping("destinos")
public class DestinoController {
    @Autowired
    DestinoRepository repository;

    @PostMapping (consumes = {"multipart/form-data"})
    @Transactional
    public ResponseEntity cadastrar(@RequestParam("foto1") MultipartFile foto1,
                                    @RequestParam("foto2") MultipartFile foto2,
                                    @RequestParam("nome") String nome,
                                    @RequestParam ("preco") BigDecimal preco,
                                    @RequestParam("meta") String meta,
                                    UriComponentsBuilder uriComponentsBuilder) throws IOException {
        var destinoASalvar = new Destino(foto1,foto2,nome, preco, meta);
        System.out.println(destinoASalvar);
        repository.save(destinoASalvar);
        var uri = uriComponentsBuilder.path("/destinos/{id}").buildAndExpand(destinoASalvar.getId()).toUri();
        return ResponseEntity.created(uri).body(new DadosDetalhamentoDestino(destinoASalvar));
    }

    @GetMapping
    public ResponseEntity listarTodos(Pageable pageable)
    {
        var page = repository.findAll(pageable).map(DadosListagemDestino::new);
        return ResponseEntity.ok(page);
    }

    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity atualizar(@PathVariable Long id,
                                    @RequestPart(value = "foto1", required = false) MultipartFile foto1,
                                    @RequestPart(value = "foto2", required = false) MultipartFile foto2,
                                    @RequestParam(value = "preco", required = false) BigDecimal preco,
                                    @RequestParam(value = "nome", required = false) String nome) throws IOException {
        var destinoAtualizado = repository.getReferenceById(id);
        destinoAtualizado.atualizar(foto1, foto2, String.valueOf(nome), preco);
        return ResponseEntity.ok(new DadosDetalhamentoDestino(destinoAtualizado));
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity deletar(@PathVariable Long id) {
        var destino = repository.getReferenceById(id);
        repository.delete(destino);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/destinos")
    public ResponseEntity encontrarPorNome(Pageable pageable,
                                           @RequestParam("nome") String nome) {
        var destinoPage = repository.findByNomeIgnoreCase(pageable, nome);
        if (destinoPage.hasContent()) {
            return ResponseEntity.ok(destinoPage);
        } else {
            return ResponseEntity.status(HttpStatus.OK).body("Nenhum destino foi encontrado");
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity detalhar(@PathVariable Long id) {
        Destino destino = repository.getReferenceById(id);
        if(Hibernate.isInitialized(destino)) {
            return ResponseEntity.ok(new DadosDetalhamentoDestino(destino));
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Nenhum destino foi encontrado");

    }
}
