package com.projeto.model;

public enum EnumTipoPessoa {

	PESSOA_FISICA1("Funcionario"), PESSOA_FISICA2("Gerente");
	
	private final String valor;

	EnumTipoPessoa(String valor) {
		this.valor = valor;
	}

	public String getValor() {
		return valor;
	}
}




