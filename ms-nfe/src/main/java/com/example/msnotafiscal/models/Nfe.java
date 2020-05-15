package com.example.msnotafiscal.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table
public class Nfe 
{
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;

	@Column
	private double valorInicial;
	
	@Column
	private double valorIRRF;
	
	@Column
	private double valorCSLL;
	
	@Column
	private double valorCofins;
	
	@Column
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