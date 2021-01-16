package com.example.Chemistry.controller;

import com.example.Chemistry.model.dao.interfaces.IChemistryDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestAttribute;

import javax.servlet.http.HttpSession;
import java.util.logging.Level;
import java.util.logging.Logger;

@Controller
public class PostRequestController {
    @Autowired
    @Qualifier("chemistryDAO")
    private IChemistryDAO dao;

    @PostMapping("/classes/create")
    public String addClass(@RequestAttribute("className") String className) {
        dao.createSubstanceClass(className);

        return "classes/create";
    }

    @PostMapping(value = {"/ions/create"})
    public String addIon(@RequestAttribute("type") String type,
                         @RequestAttribute("valence") int valence,
                         @RequestAttribute("notation") String notation) {
        dao.createIon(type, valence, notation);

        return "ions/create";
    }

    @PostMapping(value = {"/substances/create"})
    public String addSubstance(@RequestAttribute("substanceClassId") int substanceClassId,
                               @RequestAttribute("anionId") int anionId,
                               @RequestAttribute("cationId") int cationId,
                               @RequestAttribute("notation") String notation) {
        dao.createSubstanceAndFormula(substanceClassId, anionId, cationId, notation);

        return "substances/create";
    }

    @PostMapping(value = {"/login"})
    public String login(HttpSession session,
                        @ModelAttribute("username") String username,
                        @ModelAttribute("password") String password) {
        Logger.getAnonymousLogger().log(Level.WARNING, "username "+username +"password "+ password);
        if ("admin".equals(username) && "admin123".equals(password)) {
            session.setAttribute("isAdmin", true);
            return "redirect:/index";
        }
        return "login";
    }
}
