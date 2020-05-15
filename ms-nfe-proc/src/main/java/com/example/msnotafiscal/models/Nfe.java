package com.example.msnotafiscal.models;

public class Nfe 
{
	private int id;

	private double valorInicial;

	private double valorIRRF;

	private double valorCSLL;

	private double valorCofins;

	private double valorFinal;
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public double getValorInicial() {
		return valorInicial;
	}

	public void setValorInicial(double valorInicial) {
		this.valorInicial = valorInicial;
	}

	public double getValorIRRF() {
		return valorIRRF;
	}

	public void setValorIRRF(double valorIRRF) {
		this.valorIRRF = valorIRRF;
	}

	public double getValorCSLL() {
		return valorCSLL;
	}

	public void setValorCSLL(double valorCSLL) {
		this.valorCSLL = valorCSLL;
	}

	public double getValorCofins() {
		return valorCofins;
	}

	public void setValorCofins(double valorCofins) {
		this.valorCofins = valorCofins;
	}

	public double getValorFinal() {
		return valorFinal;
	}

	public void setValorFinal(double valorFinal) {
		this.valorFinal = valorFinal;
	}
}