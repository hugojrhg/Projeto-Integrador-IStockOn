package com.projeto.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Produto {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	@Column(nullable = false)
	private String nome;
	@Column(nullable = false)
	private float preco_venda;
	@Column(nullable = false)
	private float preco_compra;
	@Column(nullable = false)
	private int quantidade;
	@Column(nullable = false)
	private int vendidos;
	
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public int getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(int quantidade) {
		this.quantidade = quantidade;
	}

	public int getVendidos() {
		return vendidos;
	}

	public void setVendidos(int vendidos) {
		this.vendidos = vendidos;
	}

	public float getLucro() {

		float gastos = quantidade * preco_compra;
		float lucros = vendidos * preco_venda;
		
		float lucrototal = lucros - gastos;
		return lucrototal;
	}

	public float getPreco_venda() {
		return preco_venda;
	}

	public void setPreco_venda(float preco_venda) {
		this.preco_venda = preco_venda;
	}

	public float getPreco_compra() {
		return preco_compra;
	}

	public void setPreco_compra(float preco_compra) {
		this.preco_compra = preco_compra;
	}

}
