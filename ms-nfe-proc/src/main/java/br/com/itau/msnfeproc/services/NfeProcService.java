package br.com.itau.msnfeproc.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.msnotafiscal.models.Nfe;
import com.example.msnotafiscal.models.SolicitacaoNfe;

import br.com.itau.msnfeproc.clients.NfeClient;
import br.com.itau.msnfeproc.clients.ReceitaFederalClient;
import br.com.itau.msnfeproc.dtos.ReceitaFederalResponse;

@Service
public class NfeProcService 
{
	@Autowired
	ReceitaFederalClient receitaFederalClient;
	
	@Autowired
	NfeClient nfeClient;
	
	public void geraNfe(SolicitacaoNfe solicitacaoNfe)
	{
		Nfe nfe = new Nfe();
		
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
		
		solicitacaoNfe.setStatus("complete");
		solicitacaoNfe.setNfe(nfe);
		
		nfeClient.geraNfe(solicitacaoNfe);
	}
}
