package com.example.crud_pessoa.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class Adress {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String street;
    private Integer number;
    private String neighborhood;
    private String city;
    private String state;
    private String cep;

    @ManyToOne
    @JoinColumn(name = "id_person")
    @JsonBackReference
    private PersonModel person;
}
