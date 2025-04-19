package com.example.crud_pessoa.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Entity
@Data
public class PersonModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    private String name;

    private LocalDate dateOfBirth;

    @NotBlank
    @Column(unique = true)
    private String cpf;

    @OneToMany(mappedBy = "person", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference
    private List<Adress> Addresses;
}
