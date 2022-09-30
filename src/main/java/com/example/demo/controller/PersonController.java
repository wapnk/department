package com.example.demo.controller;

import com.example.demo.dto.PersonDTO;
import com.example.demo.model.Person;
import com.example.demo.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("person")
public class PersonController extends CrudRestController<Person, PersonDTO> {

    private final PersonService personService;

    @Autowired
    public PersonController(PersonService personService) {
        super(personService);
        this.personService = personService;
    }

    @GetMapping("testo")
    public String test() {
        return "test";
    }

}
