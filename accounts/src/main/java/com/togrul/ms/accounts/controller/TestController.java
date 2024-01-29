package com.togrul.ms.accounts.controller;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/test")
public class TestController {

    @Value("${GOPATH}")
    private  String goPath;


    @GetMapping(value = "/")
    public String test(){
        return goPath;
    }

}
