package com.example.Chemistry.controller;

import com.example.Chemistry.model.Ion;
import com.example.Chemistry.model.Substance;
import com.example.Chemistry.model.SubstanceClass;
import com.example.Chemistry.model.dao.IChemistryDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
public class ShowPagesController {
    @Autowired
    @Qualifier("chemistryDAO")
    private IChemistryDAO dao;

    @GetMapping(value = {"/", "/index"})
    public String index(HttpServletRequest request) {

        List<Substance> substances = dao.readSubstances();
        request.setAttribute("substances", substances);

        return "index";
    }

    @GetMapping(value = {"/login"})
    public String login(Model model) {
        return "login";
    }
    @GetMapping(value = {"/logout"})
    public String logout(HttpSession session) {
        session.setAttribute("isAdmin", false);
        return "index";
    }

    @GetMapping(value = {"/classes/main"})
    public String showClasses(Model model, HttpServletRequest request) {

        List<SubstanceClass> substanceClasses = dao.readSubstanceClasses();
        request.setAttribute("classes", substanceClasses);

        return "classes/main";
    }

    @GetMapping(value = {"/ions/main"})
    public String showIons(Model model, HttpServletRequest request) {
        Ion ion__ = Ion.builder().build();
//        request.setAttribute("ion__", ion__);
        model.addAttribute("ion__", ion__);

        List<Ion> ions = dao.readIons();
        request.setAttribute("ions", ions);

        return "ions/main";
    }

    @GetMapping(value = {"classes/create"})
    public String showCreateClass() {
        return "classes/create";
    }

    @GetMapping(value = {"/ions/create"})
    public String showCreateIon() {
        return "ions/create";
    }

    @GetMapping(value = {"/substances/create"})
    public String showCreateSubstance(HttpServletRequest request) {

        List<SubstanceClass> substanceClasses = dao.readSubstanceClasses();
        List<Ion> anions = dao.readIons();
        List<Ion> cations = dao.readIons();

        request.setAttribute("classes", substanceClasses);
        request.setAttribute("anions", anions);
        request.setAttribute("cations", cations);

        return "substances/create";
    }
}
