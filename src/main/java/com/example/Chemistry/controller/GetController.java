package com.example.Chemistry.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
public class GetController {

    @GetMapping(value={"/","/index"})
    public String index(){
        return "index";
    }

    @GetMapping(value = {"/login"})
    public String login(){
        return "login";
    }

    @GetMapping(value = {"/manageClass"})
    public String addClass(){
        return "manageClass";
    }

    @GetMapping(value = {"/manageIon"})
    public String addIon(){
        return "manageIon";
    }

    @GetMapping(value = {"/manageSubstance"})
    public String addSubstance(){
        return "manageSubstance";
    }
}

