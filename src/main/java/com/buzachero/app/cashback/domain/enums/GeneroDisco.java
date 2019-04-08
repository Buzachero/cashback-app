package com.buzachero.app.cashback.domain.enums;

public enum GeneroDisco {

	POP(0, "POP"),
	MPB(1, "MPB"),
	CLASSIC(2, "CLASSIC"),
	ROCK(3, "ROCK");
	
	private int codigo;
	private String nome;
	
	private GeneroDisco(int codigo, String nome) {
		this.codigo = codigo;
		this.nome = nome;
	}
		
	public int getCodigo() {
		return codigo;
	}

	public String getNome() {
		return nome;
	}
	
}
