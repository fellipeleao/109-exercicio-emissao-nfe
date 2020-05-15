package com.example.msnotafiscal.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.example.msnotafiscal.dtos.CalculaImpostoRequest;
import com.example.msnotafiscal.models.SolicitacaoNfe;
import com.example.msnotafiscal.services.NfeService;


@RestController
public class NfeController 
{
	@Autowired
	NfeService nfeService;
	
	@PostMapping("/nfe/geraNfe")
	@ResponseStatus(value = HttpStatus.OK)
	public SolicitacaoNfe geraNfe(@RequestBody CalculaImpostoRequest calculaImpostoRequest)
	{
		return nfeService.geraNfe(calculaImpostoRequest.getIdSolicitacao());
	}
	
}
