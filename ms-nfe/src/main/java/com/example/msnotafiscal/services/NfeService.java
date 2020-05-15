package com.example.msnotafiscal.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.msnotafiscal.clients.ReceitaFederalClient;
import com.example.msnotafiscal.dtos.ReceitaFederalResponse;
import com.example.msnotafiscal.models.Nfe;
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
	
	public SolicitacaoNfe geraNfe(int idSolicitacao)
	{
		Nfe nfe = new Nfe();
		SolicitacaoNfe solicitacaoNfe = solicitacaoNfeService.findById(idSolicitacao);
		
		// Nfe Pessoa Fisica
		if(solicitacaoNfe.getIdentidade().length() == 11)
		{
			nfe.setValorCofins(0);
			nfe.setValorCSLL(0);
			nfe.setValorFinal(solicitacaoNfe.getValor());
			nfe.setValorInicial(solicitacaoNfe.getValor());
			nfe.setValorIRRF(0);
			
		}
		else // Nfe Pessoa Juridica
		{
			ReceitaFederalResponse rfResp = receitaFederalClient.consultaCnpj(solicitacaoNfe.getIdentidade());
			
			// Simples Nacional
			if(rfResp.getCapital_social() < 1000000)
			{
				double irrf = (double)solicitacaoNfe.getValor() * 0.015;
				double valorFinal = (double)solicitacaoNfe.getValor() - irrf;
				
				nfe.setValorCofins(0);
				nfe.setValorCSLL(0);
				nfe.setValorFinal(valorFinal);
				nfe.setValorInicial(solicitacaoNfe.getValor());
				nfe.setValorIRRF(irrf);
			}
			else
			{
				double irrf = (double)solicitacaoNfe.getValor() * 0.015;
				double csll = (double)solicitacaoNfe.getValor() * 0.03;
				double cofins = (double)solicitacaoNfe.getValor() * 0.0065;
				double valorFinal = (double)solicitacaoNfe.getValor() - irrf - csll - cofins;
				
				nfe.setValorCofins(irrf);
				nfe.setValorCSLL(csll);
				nfe.setValorFinal(valorFinal);
				nfe.setValorInicial(solicitacaoNfe.getValor());
				nfe.setValorIRRF(irrf);
			}
		}
		
		solicitacaoNfe.setNfe(nfe);
		
		// Escreve Nfe no Banco de Dados
		nfeRepository.save(nfe);
		
		// Atualiza a SolicitacaoNfe no Banco de Dados
		SolicitacaoNfe sNfe = solicitacaoNfeService.save(solicitacaoNfe);
		
		return sNfe;
	}
}
