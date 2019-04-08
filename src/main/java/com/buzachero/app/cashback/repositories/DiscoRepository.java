package com.buzachero.app.cashback.repositories;

import org.springframework.data.domain.Page;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.buzachero.app.cashback.domain.Disco;
import com.buzachero.app.cashback.domain.enums.GeneroDisco;

@Repository
public interface DiscoRepository extends JpaRepository<Disco, Integer> {
	
	@Transactional(readOnly=true)
	Page<Disco> findByGenero(GeneroDisco genero, Pageable pageable);

}
