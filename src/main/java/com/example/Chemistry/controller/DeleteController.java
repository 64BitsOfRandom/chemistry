package com.example.Chemistry.controller;

import com.example.Chemistry.model.dao.interfaces.IAlchemyDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class DeleteController {
    @Autowired
    @Qualifier("alchemyDAO")
    private IAlchemyDAO alchemyDAO;

    @PostMapping(value={"/deleteEntity"})
    public String deleteEntity(@ModelAttribute String entity, @ModelAttribute int id){
        String redirectURL ="redirect:" +
        switch (entity){
            case IAlchemyDAO.IONS_TABLE_NAME -> "/ions/main";
            case IAlchemyDAO.CLASSES_TABLE_NAME -> "/classes/main";
            default -> "/index";
        };
        alchemyDAO.deleteEntityById(entity,id);
        return redirectURL;
    }
}
