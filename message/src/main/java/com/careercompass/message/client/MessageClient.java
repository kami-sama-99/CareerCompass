package com.careercompass.message.client;

import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.service.annotation.GetExchange;
import org.springframework.web.service.annotation.HttpExchange;

@HttpExchange
public interface MessageClient {
    @GetExchange
    public String getQuestion(@RequestParam("ans") String ans);
}
