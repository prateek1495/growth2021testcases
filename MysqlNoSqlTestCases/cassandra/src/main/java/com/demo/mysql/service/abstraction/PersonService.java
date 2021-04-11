package com.demo.mysql.service.abstraction;

import com.demo.mysql.co.CreatePersonCO;
import com.demo.mysql.co.UpdatePersonCO;
import com.demo.mysql.model.Person;

import java.util.List;

public interface PersonService {
    List<Person> getAllPersons();

    Person findById(Long id);

    Person updatePerson(UpdatePersonCO newPerson);

    String deletePerson(Long id);

    Person createPerson(CreatePersonCO newPerson);
}
