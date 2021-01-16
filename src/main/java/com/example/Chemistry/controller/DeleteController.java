package com.example.Chemistry.controller;

import com.example.Chemistry.model.beans.Ion;
import com.example.Chemistry.model.beans.Substance;
import com.example.Chemistry.model.beans.SubstanceClass;
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
    public String deleteIon(@ModelAttribute("ionToDelete") Ion ionToDelete) {
        dao.deleteIon(ionToDelete.getId());
        return "redirect:/ions/main";
    }

    @PostMapping(value = {"/classes/main"})
    public String deleteClass(@ModelAttribute("classToDelete") SubstanceClass classToDelete) {
        dao.deleteSubstanceClass(classToDelete.getId());
        return "redirect:/classes/main";
    }

    @PostMapping(value = {"/index"})
    public String deleteSubstance(@ModelAttribute("substanceToDelete") Substance substanceToDelete) {
        dao.deleteSubstanceAndFormula(substanceToDelete.getId());
        return "redirect:/index";
    }
}
