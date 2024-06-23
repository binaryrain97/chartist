package com.example.chartist.controller;

import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;

@Controller
@RequestMapping("/chat")
public class ChatController {

    @GetMapping("")
    public String chat(){
        return "chat";
    }

    @GetMapping("/new")
    public String new_chat(Principal principal, Model model) {
        model.addAttribute("user", principal.getName());
        return "new_chat";
    }
}