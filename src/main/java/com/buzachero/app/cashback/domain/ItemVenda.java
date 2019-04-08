package com.buzachero.app.cashback.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class ItemVenda implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@EmbeddedId	
	@JsonIgnore
	private ItemVendaPK id = new ItemVendaPK();
	
	private Integer quantidade;
	
	@Column(name = "cashback_total")
	private Double cashback;
	
	@Column(name = "preco_unitario")
	private Double preco;
	
	public ItemVenda() {
		
	}

	public ItemVenda(Disco disco, Venda venda, Double cashback, Integer quantidade, Double preco) {
		super();
		this.id.setDisco(disco);
		this.id.setVenda(venda);
		this.cashback = cashback;
		this.quantidade = quantidade;
		this.preco = preco;
	}
		
	public double getSubTotal() {
		return Math.round((preco * quantidade)*100.0)/100.0;
	}
	
	@JsonIgnore
	public Venda getVenda() {
		return id.getVenda();
	}
	
	public void setVenda(Venda venda) {
		id.setVenda(venda);
	}
		
	public Disco getDisco() {
		return id.getDisco();
	}
	
	public void setDisco(Disco disco) {
		id.setDisco(disco);
	}
	
	public ItemVendaPK getId() {
		return id;
	}

	public void setId(ItemVendaPK id) {
		this.id = id;
	}

	public Double getCashback() {
		return cashback;
	}

	public void setCashback(Double cashback) {
		this.cashback = cashback;
	}

	public Integer getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(Integer quantidade) {
		this.quantidade = quantidade;
	}

	public Double getPreco() {
		return preco;
	}

	public void setPreco(Double preco) {
		this.preco = preco;
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
		ItemVenda other = (ItemVenda) obj;
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
		builder.append("ItemVenda [id=");
		builder.append(id);
		builder.append(", cashback=");
		builder.append(cashback);
		builder.append(", quantidade=");
		builder.append(quantidade);
		builder.append(", preco=");
		builder.append(preco);
		builder.append("]");
		return builder.toString();
	}
	
	
	
	
}
