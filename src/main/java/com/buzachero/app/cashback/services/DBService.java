package com.buzachero.app.cashback.services;

import java.text.ParseException;
import java.time.LocalDateTime;
import java.util.Arrays;

import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.buzachero.app.cashback.domain.Disco;
import com.buzachero.app.cashback.domain.ItemVenda;
import com.buzachero.app.cashback.domain.Venda;
import com.buzachero.app.cashback.domain.enums.GeneroDisco;
import com.buzachero.app.cashback.repositories.DiscoRepository;
import com.buzachero.app.cashback.repositories.ItemVendaRepository;
import com.buzachero.app.cashback.repositories.VendaRepository;
import com.buzachero.app.cashback.utils.TabelaCashbackUtils;

@Service
public class DBService {
	@Autowired
	private TabelaCashbackUtils tabelaCashbackUtils;
	
	@Autowired
	private DiscoRepository discoRepository;
	@Autowired
	private ItemVendaRepository iVRepository;
	@Autowired
	private VendaService vendaService;
	@Autowired
	private VendaRepository vendaRepository;
	
	
	public void instantiateTestDatabase() throws ParseException {
		Disco discosPOP[] = new Disco[50];
		Disco discosMPB[] = new Disco[50];
		Disco discosCLASSIC[] = new Disco[50];
		Disco discosROCK[] = new Disco[50];		
		Venda vendas[] = new Venda[10];		
		double preco = 0.0;		
		int numeroDisco = 0;
		Random rand = new Random();	
		
		for (int i = 0; i < discosPOP.length; i++) {
			preco = rand.nextInt(50) + 20 + rand.nextDouble();
			discosPOP[i] = new Disco(null, "Disco " + (i+1), GeneroDisco.POP, Math.round(preco*100.0)/100.0);
		}
		
		for (int i = 0; i < discosMPB.length; i++) {
			preco = rand.nextInt(50) + 20 + rand.nextDouble();
			numeroDisco = discosPOP.length + i + 1;
			discosMPB[i] = new Disco(null, "Disco " + numeroDisco, GeneroDisco.MPB, Math.round(preco*100.0)/100.0);
		}
		
		for (int i = 0; i < discosCLASSIC.length; i++) {
			preco = rand.nextInt(50) + 20 + rand.nextDouble();
			numeroDisco = discosPOP.length + discosMPB.length + i + 1;
			discosCLASSIC[i] = new Disco(null, "Disco " + numeroDisco, GeneroDisco.CLASSIC, Math.round(preco*100.0)/100.0);
		}
		
		for (int i = 0; i < discosROCK.length; i++) {
			preco = rand.nextInt(50) + 20 + rand.nextDouble();
			numeroDisco = discosPOP.length + discosMPB.length + discosCLASSIC.length + i + 1;
			discosROCK[i] = new Disco(null, "Disco " + numeroDisco, GeneroDisco.ROCK, Math.round(preco*100.0)/100.0);
		}
		
		discoRepository.saveAll(Arrays.asList(discosPOP));
		discoRepository.saveAll(Arrays.asList(discosMPB));
		discoRepository.saveAll(Arrays.asList(discosCLASSIC));
		discoRepository.saveAll(Arrays.asList(discosROCK));
		
		for (int i = 0; i < vendas.length; i++) {			
			LocalDateTime currentDateTime = LocalDateTime.now();
			vendas[i] = new Venda(null, currentDateTime);
			int randomIndex = rand.nextInt(50);		
				
			int quantidadeDiscosPOP = 2;
			Double cashbackItemVenda = tabelaCashbackUtils.calculatePercentageCashback(GeneroDisco.POP, currentDateTime) 
					* discosPOP[randomIndex].getPreco()
					* quantidadeDiscosPOP;
			ItemVenda iv1 = new ItemVenda(discosPOP[randomIndex], vendas[i],  Math.round(cashbackItemVenda*100.0)/100.0, quantidadeDiscosPOP, discosPOP[randomIndex].getPreco());			
			vendas[i].getItens().add(iv1);
			
			int quantidadeDiscosMPB = 1;
			cashbackItemVenda = tabelaCashbackUtils.calculatePercentageCashback(GeneroDisco.MPB, currentDateTime) 
					* discosMPB[randomIndex].getPreco()
					* quantidadeDiscosMPB;
			ItemVenda iv2 = new ItemVenda(discosMPB[randomIndex], vendas[i], Math.round(cashbackItemVenda*100.0)/100.0, quantidadeDiscosMPB, discosMPB[randomIndex].getPreco());			
			vendas[i].getItens().add(iv2);
			
			int quantidadeDiscosCLASSIC = 1;
			cashbackItemVenda = tabelaCashbackUtils.calculatePercentageCashback(GeneroDisco.CLASSIC, currentDateTime) 
					* discosCLASSIC[randomIndex].getPreco()
					* quantidadeDiscosCLASSIC;
			ItemVenda iv3 = new ItemVenda(discosCLASSIC[randomIndex], vendas[i], Math.round(cashbackItemVenda*100.0)/100.0, quantidadeDiscosCLASSIC, discosCLASSIC[randomIndex].getPreco());			
			vendas[i].getItens().add(iv3);
			
			int quantidadeDiscosROCK = 3;
			cashbackItemVenda = tabelaCashbackUtils.calculatePercentageCashback(GeneroDisco.ROCK, currentDateTime) 
					* discosROCK[randomIndex].getPreco()
					* quantidadeDiscosROCK;
			ItemVenda iv4 = new ItemVenda(discosROCK[randomIndex], vendas[i], Math.round(cashbackItemVenda*100.0)/100.0, quantidadeDiscosROCK, discosROCK[randomIndex].getPreco());			
			vendas[i].getItens().add(iv4);
			
			vendas[i].setPrecoTotal(vendaService.calculatePrecoTotalVenda(vendas[i]));
			vendas[i].setCashbackTotal(vendaService.calculateCashback(vendas[i]));

			vendaRepository.save(vendas[i]);
			
			iVRepository.saveAll(Arrays.asList(iv1, iv2, iv3, iv4));
			
		}
		
		
		
	}
}
