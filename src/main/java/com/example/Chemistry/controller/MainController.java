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

import java.util.ArrayList;
import java.util.List;

import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

@Controller
public class MainController {
    private static List<Person> persons = new ArrayList<>();
    static {
        persons.add(new Person("Bill", "Gates"));
        persons.add(new Person("Gill", "Bates"));
    }

    @Value("${welcome.message}")
    private String message;
    @Value("${error.message}")
    private String errorMessage;

    @GetMapping(value={"/","/index"})
    public String index(Model model){
        model.addAttribute("message", message);
        return "index";
    }

    @GetMapping("/personList")
    public String personList(Model model){
        model.addAttribute("persons", persons);
        return "personList";
    }

    @GetMapping("/addPerson")
    public String showAddPersonPage(Model model){
        PersonForm personForm = new PersonForm();
        model.addAttribute("personForm", personForm);
        return "addPerson";
    }

    @PostMapping("/addPerson")
    public String savePerson(Model model, @ModelAttribute("personForm") PersonForm personForm){
        String firstName = personForm.getFirstName();
        String lastName = personForm.getLastName();
        if(!firstName.isBlank() && !lastName.isBlank()){
            Person newPerson = new Person(firstName,lastName);
            persons.add(newPerson);
            return "redirect:/personList";
        }
        model.addAttribute("errorMessage", errorMessage);
        return "addPerson";
    }

}

