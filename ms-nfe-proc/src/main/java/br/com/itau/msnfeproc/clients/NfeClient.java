package br.com.itau.msnfeproc.clients;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.msnotafiscal.models.SolicitacaoNfe;

@FeignClient(name = "nfe") 
public interface NfeClient 
{
    @PostMapping("/nfe/geraNfe")
    SolicitacaoNfe geraNfe(SolicitacaoNfe solNfe);
}