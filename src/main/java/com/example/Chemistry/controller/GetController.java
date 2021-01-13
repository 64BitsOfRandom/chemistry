package com.example.Chemistry.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
public class GetController {

    @GetMapping(value={"/","/index"})
    public String index(){
        return "index";
    }

    @GetMapping(value = {"/addClass"})
    public String addClass(){
        return "addClass";
    }

    @GetMapping(value = {"/addIon"})
    public String addIon(){
        return "addIon";
    }

    @GetMapping(value = {"/addSubstance"})
    public String addSubstance(){
        return "addSubstance";
    }

    @GetMapping(value = {"/login"})
    public String login(){
        return "login";
    }
}

