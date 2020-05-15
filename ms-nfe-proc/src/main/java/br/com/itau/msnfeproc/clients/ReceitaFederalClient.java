package br.com.itau.msnfeproc.clients;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import br.com.itau.msnfeproc.dtos.ReceitaFederalResponse;

@FeignClient(name = "receita-federal", url ="http://www.receitaws.com.br/v1/cnpj") 
public interface ReceitaFederalClient 
{
    @GetMapping("/{cnpj}")
    ReceitaFederalResponse consultaCnpj(@PathVariable(value="cnpj") String cnpj);
}