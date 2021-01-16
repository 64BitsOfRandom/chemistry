package com.example.Chemistry.controller;

import com.example.Chemistry.model.beans.Ion;
import com.example.Chemistry.model.beans.SubstanceClass;
import com.example.Chemistry.model.dao.IChemistryDAO;
import com.example.Chemistry.view.CreateSubstanceForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpSession;
import java.util.logging.Level;
import java.util.logging.Logger;

@Controller
public class PostRequestController {
    @Autowired
    @Qualifier("chemistryDAO")
    private IChemistryDAO dao;

    @PostMapping("/classes/create")
    public String addClass(@ModelAttribute("substanceClass") SubstanceClass substanceClass) {
        dao.createSubstanceClass(substanceClass.getName());
        return "redirect:/classes/main";
    }

    @PostMapping(value = {"/ions/create"})
    public String addIon(@ModelAttribute("ion") Ion ion) {
        dao.createIon(ion.getType(), ion.getValence(), ion.getNotation());
        return "redirect:/ions/main";
    }

    @PostMapping(value = {"/substances/create"})
    public String addSubstance(@ModelAttribute("substanceForm") CreateSubstanceForm substanceForm) {
        dao.createSubstanceAndFormula(substanceForm.getClassId(), substanceForm.getAnionId(), substanceForm.getCationId(), substanceForm.getNotation());
        return "redirect:/index";
    }

    @PostMapping(value = {"/login"})
    public String login(HttpSession session,
                        @ModelAttribute("username") String username,
                        @ModelAttribute("password") String password) {
        Logger.getAnonymousLogger().log(Level.WARNING, "username " + username + "password " + password);
        if ("admin".equals(username) && "admin123".equals(password)) {
            session.setAttribute("isAdmin", true);
            return "redirect:/index";
        }
        return "login";
    }
}
