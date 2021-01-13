package com.example.Chemistry.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
public class GetController {

    @GetMapping(value={"/","/index"})
    public String index(Model model){
//        model.addAttribute("message", message);
        return "index";
    }

    @GetMapping(value = {"/addClass"})
    public String addClass(Model model){
        return "addClass";
    }

    @GetMapping(value = {"/addIon"})
    public String addIon(Model model){
        return "addIon";
    }

    @GetMapping(value = {"/addSubstance"})
    public String addSubstance(Model model){
        return "addSubstance";
    }

    @GetMapping(value = {"/login"})
    public String login(Model model){
        return "login";
    }
}

