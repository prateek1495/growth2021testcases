package com.demo.mysql.service;

import com.demo.mysql.co.CreatePersonCO;
import com.demo.mysql.co.UpdatePersonCO;
import com.demo.mysql.model.Person;
import com.demo.mysql.repository.PersonRepository;
import com.demo.mysql.service.abstraction.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
public class PersonServiceImpl implements PersonService {

    @Autowired
    private PersonRepository personRepository;

    @Override
    public List<Person> getAllPersons() {
        List<Person> persons = new ArrayList<>();
        personRepository.findAll().forEach(persons::add);
        return persons;
    }

    @Override
    public Person findById(Long id) {
        return personRepository.findById(id).orElse(null);
    }

    @Override
    public Person updatePerson(UpdatePersonCO newPerson) {
        Person person = personRepository.findById(newPerson.getId()).orElse(null);
        if (Objects.nonNull(person)) {
            person.setAge(newPerson.getAge());
            person.setFullName(newPerson.getFullName());
            personRepository.save(person);
        }
        return person;
    }

    @Override
    public String deletePerson(Long id) {
        Person person = personRepository.findById(id).orElse(null);
        if (Objects.nonNull(person)) {
            personRepository.delete(person);
            return "Person deleted";
        }
        return "Person not exist";
    }

    @Override
    public Person createPerson(CreatePersonCO newPerson) {
        Person person =
                Person.builder().id(Objects.isNull(newPerson.getId()) ? System.currentTimeMillis() : newPerson.getId())
                        .fullName(newPerson.getFullName()).age(newPerson.getAge()).build();
        personRepository.save(person);
        return person;
    }
}