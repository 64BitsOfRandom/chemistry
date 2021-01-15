package com.example.Chemistry.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpSession;
import java.util.logging.Level;
import java.util.logging.Logger;

@Controller
public class PostRequestController {

    @PostMapping("/classes/create")
    public String addClass(Model model, @ModelAttribute("className") String className) {
        //TODO: impl logic
        return "classes/create";
    }

    @PostMapping(value = {"/ions/create"})
    public String addIon(Model model) {
        //TODO: impl logic
        return "ions/create";
    }

    @PostMapping(value = {"/substances/create"})
    public String addSubstance(Model model) {
        //TODO: impl logic
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
