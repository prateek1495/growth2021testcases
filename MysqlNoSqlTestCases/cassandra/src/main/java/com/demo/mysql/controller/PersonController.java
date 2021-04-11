package com.demo.mysql.controller;


import com.demo.mysql.co.CreatePersonCO;
import com.demo.mysql.co.UpdatePersonCO;
import com.demo.mysql.model.Person;
import com.demo.mysql.service.abstraction.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/person")
public class PersonController {

    @Autowired
    private PersonService personService;

    @GetMapping("/")
    public List<Person> getPersons() {
        return personService.getAllPersons();
    }

    @GetMapping("/get/{id}")
    public Person getPerson(@PathVariable Long id) {
        return personService.findById(id);
    }

    @PutMapping("/update")
    public Person updatePerson(@RequestBody UpdatePersonCO newPerson) {
        return personService.updatePerson(newPerson);
    }

    @DeleteMapping(value = "/delete/{id}")
    public String deletePerson(@PathVariable Long id) {
        return personService.deletePerson(id);
    }

    @PostMapping("/create")
    public Person addPerson(@RequestBody CreatePersonCO newPerson) {
        return personService.createPerson(newPerson);
    }
}