package com.example.msnotafiscal.dtos;

import com.example.msnotafiscal.models.Nfe;

public class ConsultaSolicitacaoNfeResponse 
{
	private String identidade;
	
	private String status;
	
	private double valor;
	
	private Nfe nfe;

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public double getValor() {
		return valor;
	}

	public void setValor(double valor) {
		this.valor = valor;
	}

	public String getIdentidade() {
		return identidade;
	}

	public void setIdentidade(String identidade) {
		this.identidade = identidade;
	}

	public Nfe getNfe() {
		return nfe;
	}

	public void setNfe(Nfe nfe) {
		this.nfe = nfe;
	}
}