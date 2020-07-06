package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/contacts")
public class ContactController {

    MyRESTController restController;

    @Autowired
    public ContactController(MyRESTController myRESTController) {
        this.restController=myRESTController;
    }


    @GetMapping("/list")
    public String listContacts(Model theModel) {

        // get Contacts from db
        List<Contact> allContacts = restController.getContacts();

        // add to the spring model
        theModel.addAttribute("contacts", allContacts);

        return "contacts/list-contacts";
    }


    @GetMapping("/search")
    public String searchContact(@RequestParam("searchString") String searchString, Model model)
    {
        List<Contact> contacts=restController.getContacts(searchString);
        model.addAttribute("contacts",contacts);
        return "contacts/list-contacts";
    }


}
