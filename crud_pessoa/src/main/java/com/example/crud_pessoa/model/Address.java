package com.example.crud_pessoa.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Schema(description = "Address entity linked to a person")
@Getter
@Setter
public class Address {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Schema(description = "Unique identifier of the address", example = "1")
    private Long id;

    @Schema(description = "Street name", example = "Main Street")
    private String street;

    @Schema(description = "House/building number", example = "100")
    private Integer number;

    @Schema(description = "Neighborhood", example = "Downtown")
    private String neighborhood;

    @Schema(description = "City", example = "New York")
    private String city;

    @Schema(description = "State abbreviation", example = "NY")
    private String state;

    @Schema(description = "ZIP/Postal code (numbers only)", example = "10001000")
    private String cep;

    @ManyToOne
    @JoinColumn(name = "person_id")
    @JsonBackReference
    private Person person;
}
