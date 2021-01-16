package com.example.Chemistry.controller;

import com.example.Chemistry.model.Ion;
import com.example.Chemistry.model.Substance;
import com.example.Chemistry.model.SubstanceClass;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
public class ShowPagesController {
    @GetMapping(value = {"/", "/index"})
    public String index(HttpServletRequest request) {

        List<Substance> substances = List.of();
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

        List<SubstanceClass> substanceClasses = List.of();
        request.setAttribute("classes", substanceClasses);

        return "classes/main";
    }

    @GetMapping(value = {"/ions/main"})
    public String showIons(HttpServletRequest request) {

        List<Ion> ions = List.of();
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

        List<SubstanceClass> substanceClasses = List.of();
        List<Ion> anions = List.of();
        List<Ion> cations = List.of();

        request.setAttribute("classes", substanceClasses);
        request.setAttribute("anions", anions);
        request.setAttribute("cations", cations);

        return "substances/create";
    }
}
