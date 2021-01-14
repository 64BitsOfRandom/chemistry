package com.example.Chemistry.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;

import java.util.logging.Logger;

@Controller
public class DeleteController {

    @DeleteMapping(value = {"/classes/main"})
    public String deleteClass(){
        //TODO: impl logic
        return "/classes/main";
    }

    @DeleteMapping(value = {"/ions/main"})
    public String deleteIon(){
        //TODO: impl logic
        return "/ions/main";
    }

    @DeleteMapping(value = {"index"})
    public String deleteSubstance(){
        Logger.getAnonymousLogger().warning("Delete method invoked!");
        //TODO: impl logic
        return "index";
    }

}
