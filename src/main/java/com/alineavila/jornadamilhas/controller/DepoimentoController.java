package com.alineavila.jornadamilhas.controller;

import com.alineavila.jornadamilhas.depoimento.DadosAtualizacaoDepoimento;
import com.alineavila.jornadamilhas.depoimento.DadosCadastroDepoimento;
import com.alineavila.jornadamilhas.depoimento.Depoimento;
import com.alineavila.jornadamilhas.depoimento.DepoimentoRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("depoimentos")
public class DepoimentoController {

    @Autowired
    DepoimentoRepository repository;

    @PostMapping
    @Transactional
    public void cadastrar(@RequestBody @Valid DadosCadastroDepoimento dados) {
        repository.save(new Depoimento(dados));
    }

    @GetMapping
    public Page<Depoimento> listarTodos(Pageable pageable) {
        return repository.findAll(pageable);
    }

    @PutMapping
    @Transactional
    public void atualizar(@RequestBody @Valid DadosAtualizacaoDepoimento dados) {
        var depoimento = repository.getReferenceById(dados.id());
        depoimento.atualizar(dados);

    }
    @DeleteMapping("/{id}")
    @Transactional
    public void excluir(@PathVariable Long id){
        var depoimento = repository.getReferenceById(id);
        repository.delete(depoimento);
    }



}
