package com.alineavila.jornadamilhas.controller;

import com.alineavila.jornadamilhas.domain.destino.DadosDetalhamentoDestino;
import com.alineavila.jornadamilhas.domain.destino.DadosListagemDestino;
import com.alineavila.jornadamilhas.domain.destino.Destino;
import com.alineavila.jornadamilhas.domain.destino.DestinoRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
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
    public ResponseEntity cadastrar(@RequestParam("foto") MultipartFile foto,
                                    @RequestParam("nome") String nome,
                                    @RequestParam("preco") BigDecimal preco,
                                    UriComponentsBuilder uriComponentsBuilder) throws IOException {
        var destinoASalvar = new Destino(foto,nome,preco);
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
                                    @RequestPart(value = "foto", required = false) MultipartFile foto,
                                    @RequestParam(value = "nome", required = false) String nome,
                                    @RequestParam(value = "preco", required = false) BigDecimal preco) throws IOException {
        var destinoAtualizado = repository.getReferenceById(id);
        destinoAtualizado.atualizar(foto, String.valueOf(nome), preco);
        return ResponseEntity.ok(new DadosDetalhamentoDestino(destinoAtualizado));
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity deletar(@PathVariable Long id) {
        var destino = repository.getReferenceById(id);
        repository.delete(destino);
        return ResponseEntity.noContent().build();
    }
}
