package br.com.itau.mslog.controllers;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

@Component
public class LogController 
{
	private static final String NOME_ARQUIVO = "logSistemaNfe.log";
	
	@KafkaListener(topics = "fellipe-biro-2", groupId = "fellipe-biro-2")
	public void recebeLog(@Payload String mensagemLog)
	{
		System.out.println("Log de Sistema: " + mensagemLog);
		
        try 
        {
            Files.write(Paths.get(NOME_ARQUIVO), mensagemLog.getBytes(), StandardOpenOption.CREATE, StandardOpenOption.APPEND);
        } 
        catch (IOException e) 
        {
            e.printStackTrace();
        }
	}
	
}
