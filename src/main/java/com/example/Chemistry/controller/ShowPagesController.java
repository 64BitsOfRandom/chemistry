package com.example.Chemistry.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

class IonView {
    public String formulaText = "111";
    public String notationText = "222";
    public String classText = "333";
}

class ClassView {
    public String formulaText = "111";
    public String notationText = "222";
    public String classText = "333";
}

class SubstanceView {
    public String formulaText = "111";
    public String notationText = "222";
    public String classText = "333";
}

@Controller
public class ShowPagesController {
    @GetMapping(value={"/","/index"})
    public String index(Model model){
        model.addAttribute("substances", List.of(new SubstanceView(), new SubstanceView()));

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

    @GetMapping(value = {"classes/create"})
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

