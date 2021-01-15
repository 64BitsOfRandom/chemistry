package com.example.Chemistry.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
public class ShowPagesController {

    @GetMapping(value={"/","/index"})
    public String index(){
        return "index";
    }

    @GetMapping(value = {"/login"})
    public String login(){
        return "login";
    }

    @GetMapping(value = {"/classes/main"})
    public String showClasses(){return "classes/main";}
    @GetMapping(value = {"/ions/main"})
    public String showIons(){return "ions/main";}

    @GetMapping(value = {"/classes/create"})
    public String showCreateClass(){
        return "classes/create";
    }
    @GetMapping(value = {"/ions/create"})
    public String showCreateIon(){
        return "ions/create";
    }
    @GetMapping(value = {"/substances/create"})
    public String showCreateSubstance(){
        return "substances/create";
    }
}

