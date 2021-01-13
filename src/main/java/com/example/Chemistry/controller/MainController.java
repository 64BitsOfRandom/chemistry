package com.example.Chemistry.controller;

import com.example.Chemistry.form.PersonForm;
import com.example.Chemistry.model.Person;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;




@Controller
public class MainController {
//    @Value("${welcome.message}")
//    private String message;
//    @Value("${error.message}")
//    private String errorMessage;

    @GetMapping(value={"/","/index"})
    public String index(Model model){
//        model.addAttribute("message", message);
        return "index";
    }

//    @PostMapping("/addPerson")
//    public String savePerson(Model model, @ModelAttribute("personForm") PersonForm personForm){
//        String firstName = personForm.getFirstName();
//        String lastName = personForm.getLastName();
//        if(!firstName.isBlank() && !lastName.isBlank()){
//            Person newPerson = new Person(firstName,lastName);
////            persons.add(newPerson);
//            return "redirect:/personList";
//        }
////        model.addAttribute("errorMessage", errorMessage);
//        return "addPerson";
//    }
}

