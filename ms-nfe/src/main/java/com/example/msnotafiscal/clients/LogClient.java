package com.example.msnotafiscal.clients;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.msnotafiscal.dtos.LogClientRequest;
import com.example.msnotafiscal.dtos.LogClientResponse;

@FeignClient(name = "log") 
public interface LogClient 
{
    @PostMapping("/log/escreveLog")
    LogClientResponse escreveLog(LogClientRequest logRequest);
}