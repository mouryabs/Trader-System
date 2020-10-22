package com.kaleidoscope.tradersystem.service;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class UIService {

    @GetMapping("/home")
    public String home(Model model) {
        return "home";
    }
}
