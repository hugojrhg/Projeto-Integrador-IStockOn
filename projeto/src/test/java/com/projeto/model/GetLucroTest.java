package com.projeto.model;

import org.junit.jupiter.api.Test;

import junit.framework.TestCase;

class GetLucroTest extends TestCase {

	Produto produto = new Produto();

	@Test
	void testGetLucro() {

		produto.setQuantidade(5);
		produto.setPreco_compra(5);
		produto.setPreco_venda(10);
		produto.setVendidos(5);

		float lucroesperado = 25.0f;
		float lucrototal = produto.getLucro();

		assertEquals(lucroesperado, lucrototal, 0);

	}

}
