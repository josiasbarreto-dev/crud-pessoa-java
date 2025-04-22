package com.example.crud_pessoa.controllers;

import com.example.crud_pessoa.model.Person;
import com.example.crud_pessoa.service.PersonService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/persons")
public class PersonController {

    @Autowired
    private PersonService service;

    @GetMapping
    public List<Person> List() {
        return service.listPerson();
    }

    @PostMapping
    public Person Create(@Valid @RequestBody Person person){
        return service.savePerson(person);
    }

    @PutMapping("/{id}")
    public Person Update(@PathVariable Long id, @Valid @RequestBody Person person){
        return service.updatePerson(id, person);
    }

    @DeleteMapping("/{id}")
    public void Delete(@PathVariable Long id) {
        service.deletePerson(id);
    }

    @GetMapping("/{id}/age")
    public int Age(@PathVariable Long id) {
        Person person = service.listPerson().stream().filter(pe -> pe.getId().equals(id)).findFirst().orElseThrow();
        return service.calculateAge(person.getDateOfBirth());
    }
}
