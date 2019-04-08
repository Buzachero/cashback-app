package com.buzachero.app.cashback.utils;

import java.time.*;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

import com.buzachero.app.cashback.domain.enums.GeneroDisco;

@Configuration
public class TabelaCashbackUtils {
	
	@Value("${param.cshback_pop_domingo}")
	private double cshbackPopDomingo;
	@Value("${param.cshback_pop_segunda}")
	private double cshbackPopSegunda;
	@Value("${param.cshback_pop_terca}")
	private double cshbackPopTerca;
	@Value("${param.cshback_pop_quarta}")
	private double cshbackPopQuarta;
	@Value("${param.cshback_pop_quinta}")
	private double cshbackPopQuinta;
	@Value("${param.cshback_pop_sexta}")
	private double cshbackPopSexta;
	@Value("${param.cshback_pop_sabado}")
	private double cshbackPopSabado;
	
	@Value("${param.cshback_mpb_domingo}")
	private double cshbackMpbDomingo;
	@Value("${param.cshback_mpb_segunda}")
	private double cshbackMpbSegunda;
	@Value("${param.cshback_mpb_terca}")
	private double cshbackMpbTerca;
	@Value("${param.cshback_mpb_quarta}")
	private double cshbackMpbQuarta;
	@Value("${param.cshback_mpb_quinta}")
	private double cshbackMpbQuinta;
	@Value("${param.cshback_mpb_sexta}")
	private double cshbackMpbSexta;
	@Value("${param.cshback_mpb_sabado}")
	private double cshbackMpbSabado;

	@Value("${param.cshback_classic_domingo}")
	private double cshbackClassicDomingo;
	@Value("${param.cshback_classic_segunda}")
	private double cshbackClassicSegunda;
	@Value("${param.cshback_classic_terca}")
	private double cshbackClassicTerca;
	@Value("${param.cshback_classic_quarta}")
	private double cshbackClassicQuarta;
	@Value("${param.cshback_classic_quinta}")
	private double cshbackClassicQuinta;
	@Value("${param.cshback_classic_sexta}")
	private double cshbackClassicSexta;
	@Value("${param.cshback_classic_sabado}")
	private double cshbackClassicSabado;

	@Value("${param.cshback_rock_domingo}")
	private double cshbackRockDomingo;
	@Value("${param.cshback_rock_segunda}")
	private double cshbackRockSegunda;
	@Value("${param.cshback_rock_terca}")
	private double cshbackRockTerca;
	@Value("${param.cshback_rock_quarta}")
	private double cshbackRockQuarta;
	@Value("${param.cshback_rock_quinta}")
	private double cshbackRockQuinta;
	@Value("${param.cshback_rock_sexta}")
	private double cshbackRockSexta;
	@Value("${param.cshback_rock_sabado}")
	private double cshbackRockSabado;		
		
	private int mapColumnIndex(DayOfWeek day) {
		int columnIndex = -1;
				
		switch (day) {
		case SUNDAY:
			columnIndex = 0;
			break;			
		case MONDAY:
			columnIndex = 1;
			break;			
		case TUESDAY:
			columnIndex = 2;
			break;
		case WEDNESDAY:
			columnIndex = 3;
			break;
		case THURSDAY:
			columnIndex = 4;
			break;
		case FRIDAY:
			columnIndex = 5;
			break;
		case SATURDAY:
			columnIndex = 6;
			break;

		default:
			break;
		}
		
		return columnIndex;
	}
	
	public Double calculatePercentageCashback(GeneroDisco generoDisco, LocalDateTime date) {	
		Double[][] tabelaCashback = { 
				{cshbackPopDomingo, cshbackPopSegunda, cshbackPopTerca, cshbackPopQuarta, cshbackPopQuinta, cshbackPopSexta, cshbackPopSabado},
				{cshbackMpbDomingo, cshbackMpbSegunda, cshbackMpbTerca, cshbackMpbQuarta, cshbackMpbQuinta, cshbackMpbSexta, cshbackMpbSabado},
				{cshbackClassicDomingo, cshbackClassicSegunda, cshbackClassicTerca, cshbackClassicQuarta, cshbackClassicQuinta, cshbackClassicSexta, cshbackClassicSabado},
				{cshbackRockDomingo, cshbackRockSegunda, cshbackRockTerca, cshbackRockQuarta, cshbackRockQuinta, cshbackRockSexta, cshbackRockSabado}
		};
		
		if(generoDisco == null || date == null) {
			return 0.0;
		}	
		
		int columnIndex = mapColumnIndex(date.getDayOfWeek());
		
		if(columnIndex == -1) {
			throw new RuntimeException("Data invalida: " + date.toString() + " Não foi possivel obter parametro de cashback!");
		}
				
		return tabelaCashback[generoDisco.getCodigo()][columnIndex];		
	}

}
