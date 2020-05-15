package br.com.itau.mslog.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class LogService 
{
	@Autowired//       (tipo chave, tipo valor)
    private KafkaTemplate<String, String> senderKfaka;
	
	public String escreveLog(String mensagemLog)
	{
		// Chama Kafka
		senderKfaka.send("fellipe-biro-2","2", mensagemLog);
		
		return "sucesso";
	}
}
