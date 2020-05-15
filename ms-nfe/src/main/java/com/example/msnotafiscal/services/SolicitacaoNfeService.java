package com.example.msnotafiscal.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import com.example.msnotafiscal.dtos.CreateSolicitacaoNfeRequest;
import com.example.msnotafiscal.models.SolicitacaoNfe;
import com.example.msnotafiscal.repositories.SolicitacaoNfeRepository;

@Service
public class SolicitacaoNfeService 
{
	@Autowired
	SolicitacaoNfeRepository solicitacaoNfeRepository;
	
	@Autowired//       Kfaka Nfe (tipo chave, tipo valor)
    private KafkaTemplate<String, SolicitacaoNfe> senderKfaka;
	
	@Autowired//       Kfaka Log (tipo chave, tipo valor)
    private KafkaTemplate<String, String> senderKfakaLog;

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
		
		// Escreve Log
		String mensagemLog =
			"[" + java.time.Clock.systemUTC().instant() + "]"
			+ "[Emissão] " 
			+ solicitacaoNfe.getIdentidade()  
			+ " acaba de pedir a emissão de uma NF no valor de "
			+ solicitacaoNfe.getValor();
		senderKfakaLog.send("fellipe-biro-2","2", mensagemLog);
		
		return sNfe;
	}
	
	public List<SolicitacaoNfe> consultaSolicitacaoNfe(String cpfcnpj)
	{
		// Chama Log
		String mensagemLog =
			  "[" + java.time.Clock.systemUTC().instant() + "]"
			+ "[Consulta] " 
			+ cpfcnpj  
			+ " acaba de pedir os dados das suas notas fiscais.";
		senderKfakaLog.send("fellipe-biro-2","2", mensagemLog);
		
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
