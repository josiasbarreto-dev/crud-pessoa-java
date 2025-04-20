package com.example.crud_pessoa.repository;

import com.example.crud_pessoa.model.PersonModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PersonRepository extends JpaRepository<PersonModel, Long> {
    boolean existsByCpf(String cpf);
}
