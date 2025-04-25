package com.example.crud_pessoa.controllers;

import com.example.crud_pessoa.model.Person;
import com.example.crud_pessoa.service.PersonService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
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
    @Operation(summary = "List all people and their respective addresses")
    public List<Person> List() {
        return service.listPerson();
    }

    @GetMapping("/paged")
    @Operation(summary = "List all people paginated")
    public Page<Person> listPaged(
            @PageableDefault(size = 5, sort = "name") Pageable pageable
    ) {
        return service.listPaged(pageable);
    }

    @PostMapping
    @Operation(summary = "Create a new person with one or more addresses")
    public ResponseEntity<Person> Create(@Valid @RequestBody Person person) {
        Person savedPerson  = service.savePerson(person);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedPerson);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update a person's details and/or their address(es)")
    public Person Update(@PathVariable Long id, @Valid @RequestBody Person person) {
        return service.updatePerson(id, person);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete a person and all their addresses")
    public void Delete(@PathVariable Long id) {
        service.deletePerson(id);
    }

    @GetMapping("/{id}/age")
    @Operation(summary = "Show Person's Age")
    public int Age(@PathVariable Long id) {
        Person person = service.listPerson().stream().filter(pe -> pe.getId().equals(id)).findFirst().orElseThrow();
        return service.calculateAge(person.getDateOfBirth());
    }
}
