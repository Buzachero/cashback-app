package com.buzachero.app.cashback.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.buzachero.app.cashback.domain.enums.GeneroDisco;

import com.buzachero.app.cashback.domain.Disco;
import com.buzachero.app.cashback.services.DiscoService;

@RestController
@RequestMapping(value = "/discos")
public class DiscoController {
	
	@Autowired
	private DiscoService discoService;

	@GetMapping(value="/{id}")
	public ResponseEntity<Disco> find(@PathVariable Integer id) {
		Disco disco = discoService.find(id);				
		return ResponseEntity.ok().body(disco);
	}
	
	@GetMapping(value="/page")
	public ResponseEntity<Page> findPageByGenero(
			@RequestParam(value="genero") GeneroDisco generoDisco,
			@RequestParam(value="pagina", defaultValue="0") Integer page, 
			@RequestParam(value="itensPorPagina", defaultValue="24") Integer linesPerPage, 
			@RequestParam(value="ordemPor", defaultValue="nome") String orderBy, 
			@RequestParam(value="direcao", defaultValue="ASC") String direction) {
		
		Page<Disco> list = discoService.findPage(generoDisco, page, linesPerPage, orderBy, direction);
		
		return ResponseEntity.ok().body(list);
	}	
}
