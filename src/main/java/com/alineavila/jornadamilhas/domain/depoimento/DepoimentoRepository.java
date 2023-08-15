package com.alineavila.jornadamilhas.domain.depoimento;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface DepoimentoRepository extends JpaRepository<Depoimento, Long> {


    @Query(value = "SELECT obj FROM Depoimento obj ORDER BY FUNCTION ('RAND')")
    public Page<Depoimento> buscarRegistrosRandomComLimitTres (Pageable pageable);
}
