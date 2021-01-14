package com.example.Chemistry.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.logging.Logger;

@Controller
public class DeleteController {

    @PostMapping(value={"/","/index"})
    public String deleteSubstance(){
        Logger.getAnonymousLogger().warning("Delete method invoked!");
        //TODO: impl logic
        return "index";
    }

    @PostMapping(value = {"/classes/main"})
    public String deleteClass(){
        Logger.getAnonymousLogger().warning("Delete method invoked!");
        //TODO: impl logic
        return "/classes/main";
    }

    @PostMapping(value = {"/ions/main"})
    public String deleteIon(){
        Logger.getAnonymousLogger().warning("Delete method invoked!");
        //TODO: impl logic
        return "/ions/main";
    }

}
