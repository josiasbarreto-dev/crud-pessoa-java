package com.example.crud_pessoa.service;

import com.example.crud_pessoa.model.PersonModel;
import com.example.crud_pessoa.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.Period;
import java.util.List;

@Service
public class PersonService {
    @Autowired
    private PersonRepository personRepository;

    public List<PersonModel> listPerson() {
        return personRepository.findAll();
    }

    public PersonModel savePerson(PersonModel person) {
        if (personRepository.existsByCpf(person.getCpf())){
            throw new RuntimeException("CPF already registered");
        }
        person.getAdress().forEach(a -> a.setPerson(person));
        return personRepository.save(person);
    }

    public PersonModel updatePerson(Long id, PersonModel updatedPerson){
        PersonModel person = personRepository.findById(id).orElseThrow();
        person.setName(updatedPerson.getName());
        person.setDateOfBirth(updatedPerson.getDateOfBirth());
        person.setCpf(updatedPerson.getCpf());

        person.getAdress().clear();
        updatedPerson.getAdress().forEach(a -> {
            a.setPerson(person);
            person.getAdress().add(a);
        });

        return personRepository.save(person);
    }

    public void deletePerson(Long id){
        personRepository.deleteById(id);
    }

    public int calculateAge(LocalDate dataOfBirth) {
        return Period.between(dataOfBirth, LocalDate.now()).getYears();
    }
}
