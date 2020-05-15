package com.example.msnotafiscal.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.msnotafiscal.clients.ReceitaFederalClient;
import com.example.msnotafiscal.models.SolicitacaoNfe;
import com.example.msnotafiscal.repositories.NfeRepository;

@Service
public class NfeService 
{
	@Autowired
	NfeRepository nfeRepository;
	
	@Autowired
	SolicitacaoNfeService solicitacaoNfeService;
	
	@Autowired
	ReceitaFederalClient receitaFederalClient;
	
	public SolicitacaoNfe geraNfe(SolicitacaoNfe solicitacaoNfe)
	{
		// Escreve Nfe no Banco de Dados
		nfeRepository.save(solicitacaoNfe.getNfe());
		
		// Atualiza a SolicitacaoNfe no Banco de Dados
		SolicitacaoNfe sNfe = solicitacaoNfeService.save(solicitacaoNfe);
		
		return sNfe;
	}
}
