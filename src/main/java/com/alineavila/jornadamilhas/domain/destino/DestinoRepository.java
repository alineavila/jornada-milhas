package com.alineavila.jornadamilhas.domain.destino;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface DestinoRepository extends JpaRepository<Destino, Long> {

    public Page<Destino> findByNomeIgnoreCase(Pageable pageable, String nome);
}
