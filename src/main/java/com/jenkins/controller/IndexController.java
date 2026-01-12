package com.jenkins.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class IndexController {
    @GetMapping("/index")
    public String index(){
        return "Welcome to the index page. Test jar name jan 12 underscore 1 jar";
    }
}
