package com.alineavila.jornadamilhas.controller;

import com.alineavila.jornadamilhas.domain.depoimento.*;
import com.alineavila.jornadamilhas.domain.depoimento.DadosDetalhamentoDepoimento;
import com.alineavila.jornadamilhas.domain.depoimento.Depoimento;
import com.alineavila.jornadamilhas.domain.depoimento.DepoimentoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.util.UriComponentsBuilder;

import java.io.IOException;

@RestController
@RequestMapping("depoimentos")
public class DepoimentoController {

    @Autowired
    DepoimentoRepository repository;

    @PostMapping
    @Transactional
    public ResponseEntity cadastrar(@RequestParam("foto") MultipartFile foto,
                                    @RequestParam("depoimento") String depoimento,
                                    @RequestParam("autor") String autor,
                                    UriComponentsBuilder uriBuilder) throws IOException {
        var depoimentoASalvar = new Depoimento(foto,depoimento,autor);
        repository.save(depoimentoASalvar);
        var uri = uriBuilder.path("/depoimentos/{id}").buildAndExpand(depoimentoASalvar.getId()).toUri();
        return ResponseEntity.created(uri).body(new DadosDetalhamentoDepoimento(depoimentoASalvar));

    }

    @GetMapping
    public ResponseEntity listarTodos(Pageable pageable) {
        var page = repository.findAll(pageable);
        return ResponseEntity.ok(page);
    }

    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity atualizar(@PathVariable("id") Long id,
                          @RequestPart(value = "foto", required = false) MultipartFile foto,
                          @RequestParam(value = "depoimento", required = false) String depoimento,
                          @RequestParam(value = "autor", required = false) String autor) throws IOException {
        var depoimentoAtualizar = repository.getReferenceById(id);
        depoimentoAtualizar.atualizar(foto, String.valueOf(depoimento),autor);
        return ResponseEntity.ok(new DadosDetalhamentoDepoimento(depoimentoAtualizar));

    }
    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity excluir(@PathVariable Long id){
        var depoimento = repository.getReferenceById(id);
        repository.delete(depoimento);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/depoimentos-home")
    public ResponseEntity
    listarHome(@PageableDefault(size = 3) Pageable pageable) {

        var page = repository.buscarRegistrosRandomComLimitTres(pageable);
        return ResponseEntity.ok(page);
    }



}
