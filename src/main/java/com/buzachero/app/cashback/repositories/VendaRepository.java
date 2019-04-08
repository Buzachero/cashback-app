package com.buzachero.app.cashback.repositories;


import java.time.LocalDateTime;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.buzachero.app.cashback.domain.Venda;

@Repository
public interface VendaRepository extends JpaRepository<Venda, Integer> {
	
	@Query("SELECT venda FROM Venda venda WHERE venda.dataVenda BETWEEN :dataInicial AND :dataFinal ORDER BY venda.dataVenda DESC")
	@Transactional(readOnly=true)
	public Page<Venda> findVendas(@Param("dataInicial") LocalDateTime dataInicial, @Param("dataFinal") LocalDateTime dataFinal, Pageable pageable);

}
