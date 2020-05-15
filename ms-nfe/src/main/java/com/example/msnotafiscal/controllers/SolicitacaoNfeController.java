package com.example.msnotafiscal.controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.example.msnotafiscal.dtos.CreateSolicitacaoNfeRequest;
import com.example.msnotafiscal.dtos.CreateSolicitacaoNfeResponse;
import com.example.msnotafiscal.dtos.ConsultaSolicitacaoNfeResponse;
import com.example.msnotafiscal.models.SolicitacaoNfe;
import com.example.msnotafiscal.services.SolicitacaoNfeService;


@RestController
public class SolicitacaoNfeController 
{
	@Autowired
	SolicitacaoNfeService solicitacaoNfeService;
	
	@PostMapping("/nfe/emitir")
	@ResponseStatus(value = HttpStatus.CREATED)
	public CreateSolicitacaoNfeResponse criaSolicitacaoNfe(@RequestBody CreateSolicitacaoNfeRequest createNfeRequest)
	{
		SolicitacaoNfe solNfe = solicitacaoNfeService.criaSolicitacaoNfe(createNfeRequest);
		
		CreateSolicitacaoNfeResponse cResp = new CreateSolicitacaoNfeResponse();
		cResp.setStatus(solNfe.getStatus());
		return cResp;
	}
	
	@GetMapping("/nfe/consultar/{cpfcnpj}")
	@ResponseStatus(value = HttpStatus.OK)
	public List<ConsultaSolicitacaoNfeResponse> consultaSolicitacaoNfe(@PathVariable(value="cpfcnpj") String cpfcnpj)
	{
		List<ConsultaSolicitacaoNfeResponse> listConsultaNfeResp = new ArrayList<ConsultaSolicitacaoNfeResponse>();
		List<SolicitacaoNfe> listSolNfe = solicitacaoNfeService.consultaSolicitacaoNfe(cpfcnpj);
		
		for(SolicitacaoNfe solNfe : listSolNfe)
		{
			ConsultaSolicitacaoNfeResponse novaConsulta = new ConsultaSolicitacaoNfeResponse();
			novaConsulta.setIdentidade(solNfe.getIdentidade());
			novaConsulta.setStatus(solNfe.getStatus());
			novaConsulta.setValor(solNfe.getValor());
			novaConsulta.setNfe(solNfe.getNfe());
			listConsultaNfeResp.add(novaConsulta);
		}
		
		return listConsultaNfeResp;
	}
}
