package com.example.firstproject.api;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController // RestAPI 컨트롤러! JSON을 반환!
public class FirstApiController {
    @GetMapping
    public String hello(){
        return "hello world!";
    }
}
