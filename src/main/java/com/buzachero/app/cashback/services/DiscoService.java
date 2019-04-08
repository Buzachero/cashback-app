package com.buzachero.app.cashback.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.buzachero.app.cashback.domain.Disco;
import com.buzachero.app.cashback.domain.enums.GeneroDisco;
import com.buzachero.app.cashback.repositories.DiscoRepository;

import com.buzachero.app.cashback.services.exceptions.ObjectNotFoundException;

@Service
public class DiscoService {

	@Autowired
	private DiscoRepository discoRepository;
	
	public Disco find(Integer discoId) {
		Optional<Disco> disco = discoRepository.findById(discoId);
		return disco.orElseThrow(() -> new ObjectNotFoundException(
				"Objeto nao encontrado! Id: " + discoId + ", Tipo: " + Disco.class.getName()));
		
	}
	
	@Transactional
	public Disco insert(Disco novoDisco) {
		novoDisco.setId(null);		
		novoDisco = discoRepository.save(novoDisco);		
		return novoDisco;
	}
	
	public Page<Disco> findPage(GeneroDisco generoDisco, Integer page, Integer linesPerPage, String orderBy, String direction) {
		Pageable pageable = PageRequest.of(page, linesPerPage, Direction.valueOf(direction), orderBy); 
		
		return discoRepository.findByGenero(generoDisco, pageable);
	}
	
	
}
