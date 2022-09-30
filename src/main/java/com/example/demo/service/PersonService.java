package com.example.demo.service;

import com.example.demo.dto.PersonDTO;
import com.example.demo.model.Person;
import com.example.demo.repo.PersonRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.awt.print.Pageable;
import java.util.List;

@Service
public class PersonService extends CrudService<Person, PersonDTO> {

    private final PersonRepo personRepo;

    @Autowired
    public PersonService(PersonRepo personRepo) {
        super(personRepo);
        this.personRepo = personRepo;
    }

    @Override
    public List<Person> getAllSorted(Pageable pageable) {
        return null;
    }
}