package com.example.Chemistry.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class PostController {

    @PostMapping("/manageClass")
    public String addClass(Model model, @ModelAttribute("className")String className){
        //TODO: impl logic
        return "manageClass";
    }

    @PostMapping(value = {"/manageIon"})
    public String addIon(Model model){
        //TODO: impl logic
        return "manageIon";
    }

    @PostMapping(value = {"/manageSubstance"})
    public String addSubstance(Model model){
        //TODO: impl logic
        return "manageSubstance";
    }

    @PostMapping(value = {"/login"})
    public String login(Model model, @ModelAttribute("login")String login,@ModelAttribute("password")String password){
        //TODO: impl logic
        return "login";
    }
}
