package com.buzachero.app.cashback.services;


import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.buzachero.app.cashback.domain.Disco;
import com.buzachero.app.cashback.domain.ItemVenda;
import com.buzachero.app.cashback.domain.Venda;
import com.buzachero.app.cashback.repositories.ItemVendaRepository;
import com.buzachero.app.cashback.repositories.VendaRepository;
import com.buzachero.app.cashback.services.exceptions.InvalidPeriod;
import com.buzachero.app.cashback.services.exceptions.ObjectNotFoundException;
import com.buzachero.app.cashback.utils.TabelaCashbackUtils;

@Service
public class VendaService {
	@Autowired
	private TabelaCashbackUtils tabelaCashbackUtils;
	
	@Autowired
	private DiscoService discoService;	
	@Autowired
	private ItemVendaRepository iVRepository;	
	@Autowired
	private VendaRepository vendaRepository;
	
	public Venda find(Integer vendaId) {
		
		Optional<Venda> venda = vendaRepository.findById(vendaId);
		return venda.orElseThrow(() -> new ObjectNotFoundException(
				"Objeto nao encontrado! Id: " + vendaId + ", Tipo: " + Venda.class.getName()));
	}
	
	@Transactional
	public Venda insert(Venda novaVenda) {
		novaVenda.setId(null);
		novaVenda.setDataVenda(LocalDateTime.now());	
		
		for (ItemVenda itemVenda : novaVenda.getItens()) {
			Integer discoId = itemVenda.getDisco().getId();
			Disco disco = discoService.find(discoId);
			itemVenda.setDisco(disco);
			itemVenda.setPreco(disco.getPreco());
			itemVenda.setCashback(calculateCashback(itemVenda));
			itemVenda.setVenda(novaVenda);
		}	
		
		novaVenda.setPrecoTotal(calculatePrecoTotalVenda(novaVenda));
		novaVenda.setCashbackTotal(calculateCashback(novaVenda));			
		novaVenda = vendaRepository.save(novaVenda);		
		
		iVRepository.saveAll(novaVenda.getItens());	
		
		return novaVenda;
	}
	
	public Page<Venda> findPage(Integer page, Integer linesPerPage, String orderBy, String direction, String dataVendaInicial, String dataVendaFinal) {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		LocalDate initialDate = null;
		LocalDate finalDate = null;
		Pageable pageable = PageRequest.of(page, linesPerPage, Direction.valueOf(direction), orderBy);
		
		initialDate = LocalDate.parse(dataVendaInicial, formatter);
		finalDate = LocalDate.parse(dataVendaFinal, formatter);
		
		if(initialDate.isAfter(finalDate)) {
			throw new InvalidPeriod("Data inicial " + dataVendaInicial + " é posterior a data final " + dataVendaFinal);
		}
		
		return vendaRepository.findVendas(LocalDateTime.of(initialDate, LocalTime.of(0, 0)), LocalDateTime.of(finalDate, LocalTime.of(23, 59)), pageable);		
	}
	
	protected Double calculateCashback(ItemVenda itemVenda) {	
		if(itemVenda == null || itemVenda.getDisco() == null) {
			return 0.0;
		}	
		
		Double cashback = tabelaCashbackUtils.calculatePercentageCashback(itemVenda.getDisco().getGenero(), LocalDateTime.now())
				* itemVenda.getDisco().getPreco()
				* itemVenda.getQuantidade();
		
		return Math.round(cashback*100.0)/100.0;
	}
	
	protected Double calculateCashback(Venda venda) {		
		Double soma = 0.00;
		
		if(venda == null || venda.getItens().size() == 0) {
			return 0.00;
		}
		
		for (ItemVenda itemVenda : venda.getItens()) {
			soma += itemVenda.getCashback();
		}
		
		return Math.round(soma*100.0)/100.0;
	}
	
	protected Double calculatePrecoTotalVenda(Venda venda) {
		Double soma = 0.00;
		
		if(venda == null || venda.getItens().size() == 0) {
			return 0.00;
		}

		for (ItemVenda itemVenda : venda.getItens()) {
			soma += itemVenda.getSubTotal();
		}

		return Math.round(soma*100.0)/100.0;		
	}
	
}
