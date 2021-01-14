package com.example.Chemistry.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.logging.Logger;

@Controller
public class DeleteController {

    @PostMapping(value={"/deleteSubstance"})
    public String deleteSubstance(){
        Logger.getAnonymousLogger().warning("Delete method invoked!");
        //TODO: impl logic
        return "redirect:/index";
    }

    @PostMapping(value = {"/deleteClass"})
    public String deleteClass(){
        Logger.getAnonymousLogger().warning("Delete method invoked!");
        //TODO: impl logic
        return "redirect:/classes/main";
    }

    @PostMapping(value = {"/deleteIon"})
    public String deleteIon(){
        Logger.getAnonymousLogger().warning("Delete method invoked!");
        //TODO: impl logic
        return "redirect:/ions/main";
    }

}
