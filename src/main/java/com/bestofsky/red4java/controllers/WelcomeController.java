package com.bestofsky.red4java.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class WelcomeController {
    @GetMapping("/")
    private String index() {
        return "welcome/index";
    }
}
