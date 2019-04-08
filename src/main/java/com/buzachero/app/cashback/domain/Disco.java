package com.buzachero.app.cashback.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import com.buzachero.app.cashback.domain.enums.GeneroDisco;
import com.fasterxml.jackson.annotation.JsonIgnore;



@Entity
public class Disco implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	@NotEmpty
	private String nome;	
	private GeneroDisco genero;	
	@NotNull
	private Double preco;
	
	@OneToMany(mappedBy="id.disco")
	@JsonIgnore
	private Set<ItemVenda> itens = new HashSet<>();
	
	public Disco() {
		
	}
		
	public Disco(Integer id, @NotEmpty String nome, GeneroDisco genero, @NotNull Double preco) {
		super();
		this.id = id;
		this.nome = nome;
		this.genero = genero;
		this.preco = preco;
	}
	
	@JsonIgnore
	public List<Venda> getVendas() {
		List<Venda> lista = new ArrayList<>();
		for (ItemVenda itemVenda : itens) {
			lista.add(itemVenda.getVenda());
		}
		
		return lista;
	}

	public Integer getId() {
		return id;
	}
	
	public void setId(Integer id) {
		this.id = id;
	}
	
	public String getNome() {
		return nome;
	}
	
	public void setNome(String nome) {
		this.nome = nome;
	}
	
	public GeneroDisco getGenero() {
		return genero;
	}
	
	public void setGenero(GeneroDisco genero) {
		this.genero = genero;
	}
	
	public Double getPreco() {
		return preco;
	}
	
	public void setPreco(Double preco) {
		this.preco = preco;
	}
	

	public Set<ItemVenda> getItens() {
		return itens;
	}

	public void setItens(Set<ItemVenda> itens) {
		this.itens = itens;
	}

	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((preco == null) ? 0 : preco.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Disco other = (Disco) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (preco == null) {
			if (other.preco != null)
				return false;
		} else if (!preco.equals(other.preco))
			return false;
		return true;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Disco [id=");
		builder.append(id);
		builder.append(", nome=");
		builder.append(nome);
		builder.append(", genero=");
		builder.append(genero);
		builder.append(", preco=");
		builder.append(preco);
		builder.append("]");
		return builder.toString();
	}
	
}
