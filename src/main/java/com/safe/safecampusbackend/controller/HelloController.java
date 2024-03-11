package com.safe.safecampusbackend.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {
    @GetMapping("/")
    public String home(){
        return "<h1>欢迎来到反诈校园!</h1>";
    }

    @GetMapping("/hello")
    public String hello(){
        return "<h1>Hello World</h1>";
    }
}
