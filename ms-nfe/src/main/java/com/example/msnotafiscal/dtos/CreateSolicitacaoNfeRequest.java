package com.example.msnotafiscal.dtos;

public class CreateSolicitacaoNfeRequest 
{
	private String identidade;
	
	private Double valor;

	public String getIdentidade() {
		return identidade;
	}

	public void setIdentidade(String identidade) {
		this.identidade = identidade;
	}

	public Double getValor() {
		return valor;
	}

	public void setValor(Double valor) {
		this.valor = valor;
	}
}
