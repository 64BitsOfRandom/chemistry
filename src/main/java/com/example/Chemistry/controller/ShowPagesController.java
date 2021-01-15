package com.example.Chemistry.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Random;

class IonView {
    public int id = new Random().nextInt();
    public String typeText = "type";
    public String notationText = "notation";
    public String valenceText = "valence";
}

class ClassView {
    public int id = new Random().nextInt();
    public String nameText = "name";
}

class SubstanceView {
    public int id = new Random().nextInt();
    public String formulaText = "formula";
    public String notationText = "notation";
    public String classText = "class";
}

@Controller
public class ShowPagesController {
    List<SubstanceView> substances;
    List<ClassView> classes;
    List<IonView> ions;
    List<IonView> anions;
    List<IonView> cations;

    @GetMapping(value = {"/", "/index"})
    public String index(Model model, HttpServletRequest request) {

        substances = List.of(new SubstanceView(), new SubstanceView());
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

        classes = List.of(new ClassView(), new ClassView());
        request.setAttribute("classes", classes);

        return "classes/main";
    }

    @GetMapping(value = {"/ions/main"})
    public String showIons(Model model, HttpServletRequest request) {

        ions = List.of(new IonView(), new IonView());
        request.setAttribute("ions", ions);

        return "ions/main";
    }

    @GetMapping(value = {"classes/create"})
    public String showCreateClass(Model model) {
        return "classes/create";
    }

    @GetMapping(value = {"/ions/create"})
    public String showCreateIon(Model model) {
        return "ions/create";
    }

    @GetMapping(value = {"/substances/create"})
    public String showCreateSubstance(Model model, HttpServletRequest request) {

        classes = List.of(new ClassView(), new ClassView());
        anions = List.of(new IonView(), new IonView());
        cations = List.of(new IonView(), new IonView());

        request.setAttribute("classes", classes);
        request.setAttribute("anions", anions);
        request.setAttribute("cations", cations);

        return "substances/create";
    }
}

