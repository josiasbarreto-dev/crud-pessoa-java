package com.example.crud_pessoa.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Schema(description = "Entity representing a person with name, CPF, date of birth, and addresses")
@Getter
@Setter
public class Person {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Schema(description = "Unique identifier of the person", example = "1")
    private Long id;

    @NotBlank(message = "Name is required")
    @Schema(description = "Full name of the person", example = "John Doe")
    private String name;

    @Schema(description = "Date of birth (yyyy-MM-dd)", example = "1990-01-01")
    private LocalDate dateOfBirth;

    @NotBlank(message = "CPF is required")
    @Column(unique = true)
    @Schema(description = "Brazilian CPF (only numbers)", example = "12345678901")
    private String cpf;

    @OneToMany(mappedBy = "person", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference
    @Schema(description = "List of addresses associated with the person")
    private List<Address> addresses = new ArrayList<>();
}