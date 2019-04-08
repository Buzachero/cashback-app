package com.buzachero.app.cashback.domain;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonFormat;


@Entity
public class Venda implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	
	@JsonFormat(pattern="dd/MM/yyyy HH:mm:ss")
	@Column(name = "data_venda", columnDefinition = "TIMESTAMP")
	private LocalDateTime dataVenda;
	
	@Column(name = "preco_total")
	private Double precoTotal;
	
	@Column(name = "cashback_total")
	private Double cashbackTotal;
	
	@OneToMany(mappedBy="id.venda")
	private Set<ItemVenda> itens = new HashSet<>();
	
	public Venda() {
		
	}
	
	public Venda(Integer id, LocalDateTime dataVenda) {
		super();
		this.id = id;
		this.dataVenda = dataVenda;
	}	

	public Integer getId() {
		return id;
	}
	
	public void setId(Integer id) {
		this.id = id;
	}
		
	public LocalDateTime getDataVenda() {
		return dataVenda;
	}
	
	public void setDataVenda(LocalDateTime dataVenda) {
		this.dataVenda = dataVenda;
	}
	
	public Double getPrecoTotal() {
		return precoTotal;
	}

	public void setPrecoTotal(Double precoTotal) {
		this.precoTotal = precoTotal;
	}

	public Double getCashbackTotal() {
		return cashbackTotal;
	}

	public void setCashbackTotal(Double cashbackTotal) {
		this.cashbackTotal = cashbackTotal;
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
		Venda other = (Venda) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Venda [id=");
		builder.append(id);
		builder.append(", dataVenda=");
		builder.append(dataVenda);
		builder.append(", itens=");
		builder.append(itens);
		builder.append("]");
		return builder.toString();
	}
		
}
