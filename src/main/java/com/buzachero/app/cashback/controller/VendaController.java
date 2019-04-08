package com.buzachero.app.cashback.controller;

import java.net.URI;
import java.time.format.DateTimeParseException;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.buzachero.app.cashback.domain.Venda;
import com.buzachero.app.cashback.services.VendaService;

@RestController
@RequestMapping(value = "/vendas")
public class VendaController {
	
	@Autowired
	private VendaService vendaService;
	
	@GetMapping(value="/{id}")
	public ResponseEntity<Venda> find(@PathVariable Integer id) {
		Venda venda = vendaService.find(id);				
		return ResponseEntity.ok().body(venda);
	}
		
	@PostMapping()
	public ResponseEntity<Void> insert(@RequestBody Venda novaVenda) {
		Venda venda = vendaService.insert(novaVenda);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
				.path("/{id}").buildAndExpand(venda.getId()).toUri();
		
		return ResponseEntity.created(uri).build();
	}
	
	@GetMapping(value="/page")
	public ResponseEntity<Page> findPageByPeriod(
			@RequestParam(value="pagina", defaultValue="0") Integer page, 
			@RequestParam(value="itensPorPagina", defaultValue="5") Integer linesPerPage, 
			@RequestParam(value="ordemPor", defaultValue="dataVenda") String orderBy, 
			@RequestParam(value="direcao", defaultValue="DESC") String direction,
			@RequestParam(value="dataVendaInicial") String dataVendaInicial,
			@RequestParam(value="dataVendaFinal") String dataVendaFinal) {
		
		Page<Venda> list = vendaService.findPage(page, linesPerPage, orderBy, direction, dataVendaInicial, dataVendaFinal);		
	
		return ResponseEntity.ok().body(list);
	}	

}
