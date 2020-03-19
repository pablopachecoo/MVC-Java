package com.gft.mvc.model;

public enum StatusTitulo {
	PENDENTE("Pendente"),
	QUITADO("Quitado"),
	RECEBIDO("Recebido");
	private String descricao;
	
	StatusTitulo(String descricao){
		this.descricao = descricao;
	}

	public String getDescricao() {
		return descricao;
	}
}
