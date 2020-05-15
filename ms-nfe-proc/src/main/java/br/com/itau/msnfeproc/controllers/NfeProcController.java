package br.com.itau.msnfeproc.controllers;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

import com.example.msnotafiscal.models.SolicitacaoNfe;

@Component
public class NfeProcController 
{
    @KafkaListener(topics = "fellipe-biro-1", groupId = "fellipe-biro-1")
    public void recebeDadosNfe(@Payload SolicitacaoNfe solicitacaoNfe) {
        System.out.println("Recebi dados do Kafka: " + solicitacaoNfe.getIdSolicitacao() + " " + solicitacaoNfe.getValor());
    }
}
