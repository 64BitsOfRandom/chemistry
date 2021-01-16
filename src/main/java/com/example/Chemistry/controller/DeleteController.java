package com.example.Chemistry.controller;

import com.example.Chemistry.model.Ion;
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
        System.out.printf("delete ion %s\n", ion__);
        return "/ions/main";
    }
}
