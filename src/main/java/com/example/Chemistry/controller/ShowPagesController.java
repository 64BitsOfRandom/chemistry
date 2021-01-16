package com.example.Chemistry.controller;

import com.example.Chemistry.model.beans.Ion;
import com.example.Chemistry.model.beans.Substance;
import com.example.Chemistry.model.beans.SubstanceClass;
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
    public String index(Model model, HttpServletRequest request) {
        Substance substance = Substance.builder().build();
        model.addAttribute("substanceToDelete", substance);

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
        SubstanceClass substanceClass = SubstanceClass.builder().build();
        model.addAttribute("classToDelete", substanceClass);

        List<SubstanceClass> substanceClasses = dao.readSubstanceClasses();
        request.setAttribute("classes", substanceClasses);

        return "classes/main";
    }

    @GetMapping(value = {"/ions/main"})
    public String showIons(Model model, HttpServletRequest request) {
        Ion ionToDelete = Ion.builder().build();
        model.addAttribute("ionToDelete", ionToDelete);

        List<Ion> ions = dao.readIons();
        request.setAttribute("ions", ions);

        return "ions/main";
    }

    @GetMapping(value = {"classes/create"})
    public String showCreateClass(Model model) {
        model.addAttribute("substanceClass", SubstanceClass.builder().build());
        return "classes/create";
    }

    @GetMapping(value = {"/ions/create"})
    public String showCreateIon(Model model) {
        model.addAttribute("ion", Ion.builder().build());
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
