package com.example.crud_pessoa.repository;

import com.example.crud_pessoa.model.Person;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PersonRepository extends JpaRepository<Person, Long> {
    boolean existsByCpf(String cpf);
}