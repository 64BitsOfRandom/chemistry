package com.example.Chemistry.controller;

import com.example.Chemistry.model.Ion;
import com.example.Chemistry.model.Substance;
import com.example.Chemistry.model.SubstanceClass;
import com.example.Chemistry.model.dao.IChemistryDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class DeleteController {
    @Autowired
    @Qualifier("chemistryDAO")
    private IChemistryDAO dao;

    @PostMapping(value = {"/ions/main"})
    public String deleteIon(@ModelAttribute("ion__") Ion ion__) {
        int id = ion__.id;
        System.out.printf("delete ion %s\n", id);

        return "redirect:/ions/main";
    }

    @PostMapping(value = {"/classes/main"})
    public String deleteClass(@ModelAttribute("class__") SubstanceClass class__) {
        int id = class__.id;
        System.out.printf("delete class__ %s\n", id);

        return "redirect:/classes/main";
    }

    @PostMapping(value = {"/index"})
    public String deleteSubstance(@ModelAttribute("substance__") Substance substance__) {
        int id = substance__.id;
        System.out.printf("delete substance__ %s\n", id);

        return "redirect:/index";
    }
}
