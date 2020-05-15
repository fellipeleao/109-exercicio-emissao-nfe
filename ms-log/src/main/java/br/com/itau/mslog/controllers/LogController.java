package br.com.itau.mslog.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.com.itau.mslog.dtos.CreateLogRequest;
import br.com.itau.mslog.dtos.CreateLogResponse;
import br.com.itau.mslog.services.LogService;

@RestController
public class LogController 
{
	@Autowired
	LogService logService;
	
	@PostMapping("/log/escreveLog")
	@ResponseStatus(value = HttpStatus.CREATED)
	public CreateLogResponse escreveLog(@RequestBody CreateLogRequest createLogRequest)
	{
		CreateLogResponse cResp = new CreateLogResponse();
		cResp.setLogStatus(logService.escreveLog(createLogRequest.getMensagemLog()));
		return cResp;
	}
	
}
