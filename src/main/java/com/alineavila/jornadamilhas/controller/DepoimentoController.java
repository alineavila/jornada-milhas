package com.alineavila.jornadamilhas.controller;

import com.alineavila.jornadamilhas.depoimento.DadosAtualizacaoDepoimento;
import com.alineavila.jornadamilhas.depoimento.DadosCadastroDepoimento;
import com.alineavila.jornadamilhas.depoimento.Depoimento;
import com.alineavila.jornadamilhas.depoimento.DepoimentoRepository;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("depoimentos")
public class DepoimentoController {

    @Autowired
    DepoimentoRepository repository;

    @PostMapping
    @Transactional
    public void cadastrar(@RequestPart("foto") MultipartFile foto,
                          @RequestPart("depoimento") String depoimento,
                          @RequestPart("autor") String autor) throws IOException {
        repository.save(new Depoimento(foto,depoimento,autor));
    }

    @GetMapping
    public Page<Depoimento> listarTodos(Pageable pageable) {
        return repository.findAll(pageable);
    }

    @PutMapping("/{id}")
    @Transactional
    public void atualizar(@PathVariable("id") Long id,
                          @RequestPart(value = "foto", required = false) MultipartFile foto,
                          @RequestParam(value = "depoimento", required = false) String depoimento,
                          @RequestParam(value = "autor", required = false) String autor) throws IOException {
        var depoimentoAtual = repository.getReferenceById(id);
        depoimentoAtual.atualizar(foto, String.valueOf(depoimento),autor);

    }
    @DeleteMapping("/{id}")
    @Transactional
    public void excluir(@PathVariable Long id){
        var depoimento = repository.getReferenceById(id);
        repository.delete(depoimento);
    }

    @GetMapping("/depoimentos-home")
    public Page<Depoimento> listarHome(@PageableDefault(size = 3) Pageable pageable) {

        return repository.buscarRegistrosRandomComLimitTres(pageable);
    }



}
