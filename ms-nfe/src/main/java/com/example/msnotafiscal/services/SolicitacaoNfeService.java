package com.example.msnotafiscal.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import com.example.msnotafiscal.clients.LogClient;
import com.example.msnotafiscal.dtos.CreateSolicitacaoNfeRequest;
import com.example.msnotafiscal.dtos.LogClientRequest;
import com.example.msnotafiscal.models.SolicitacaoNfe;
import com.example.msnotafiscal.repositories.SolicitacaoNfeRepository;

@Service
public class SolicitacaoNfeService 
{
	@Autowired
	SolicitacaoNfeRepository solicitacaoNfeRepository;
	
	@Autowired
	LogClient logClient;
	
	@Autowired//       (tipo chave, tipo valor)
    private KafkaTemplate<String, SolicitacaoNfe> senderKfaka;

	public SolicitacaoNfe criaSolicitacaoNfe(CreateSolicitacaoNfeRequest createNfeRequest)
	{
		SolicitacaoNfe solicitacaoNfe = new SolicitacaoNfe();
		solicitacaoNfe.setIdentidade(createNfeRequest.getIdentidade());
		solicitacaoNfe.setValor(createNfeRequest.getValor());
		solicitacaoNfe.setStatus("pending");
		
		// Escreve no Banco de Dados
		SolicitacaoNfe sNfe = solicitacaoNfeRepository.save(solicitacaoNfe);
		
		// Chama Kafka
		senderKfaka.send("fellipe-biro-1","1", sNfe);
		
		// Chama Log
		LogClientRequest logReq = new LogClientRequest();
		logReq.setMensagemLog(
				  "[" + java.time.Clock.systemUTC().instant() + "]"
				+ "[Emissão] " 
				+ solicitacaoNfe.getIdentidade()  
				+ " acaba de pedir a emissão de uma NF no valor de "
				+ solicitacaoNfe.getValor());
		logClient.escreveLog(logReq);
		
		return sNfe;
	}
	
	public List<SolicitacaoNfe> consultaSolicitacaoNfe(String cpfcnpj)
	{
		// Chama Log
		LogClientRequest logReq = new LogClientRequest();
		logReq.setMensagemLog(
				  "[" + java.time.Clock.systemUTC().instant() + "]"
				+ "[Consulta] " 
				+ cpfcnpj  
				+ " acaba de pedir os dados das suas notas fiscais.");
		logClient.escreveLog(logReq);
		
		return solicitacaoNfeRepository.findAllByIdentidade(cpfcnpj);
	}
	
	public SolicitacaoNfe findById(int idSolicitacao)
	{
		return solicitacaoNfeRepository.findById(idSolicitacao);
	}
	
	public SolicitacaoNfe save(SolicitacaoNfe solNfe)
	{
		return solicitacaoNfeRepository.save(solNfe);
	}
}
