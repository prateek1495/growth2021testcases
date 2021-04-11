package com.demo.cassandra.service.abstraction;

import com.demo.cassandra.co.CreatePersonCO;
import com.demo.cassandra.co.UpdatePersonCO;
import com.demo.cassandra.model.Person;

import java.util.List;

public interface PersonService {
    List<Person> getAllPersons();

    Person findById(Long id);

    Person updatePerson(UpdatePersonCO newPerson);

    String deletePerson(Long id);

    Person createPerson(CreatePersonCO newPerson);
}
