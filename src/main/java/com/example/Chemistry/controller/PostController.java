package com.example.Chemistry.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class PostController {

    @PostMapping("/classes/create")
    public String addClass(Model model, @ModelAttribute("className")String className){
        //TODO: impl logic
        return "classes/create";
    }

    @PostMapping(value = {"/ions/create"})
    public String addIon(Model model){
        //TODO: impl logic
        return "ions/create";
    }

    @PostMapping(value = {"/substances/create"})
    public String addSubstance(Model model){
        //TODO: impl logic
        return "substances/create";
    }

    @PostMapping(value = {"/login"})
    public String login(Model model, @ModelAttribute("login")String login,@ModelAttribute("password")String password){
        //TODO: impl logic
        return "login";
    }
}
