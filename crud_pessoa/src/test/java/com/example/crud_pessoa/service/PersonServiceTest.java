package com.example.crud_pessoa.service;

import com.example.crud_pessoa.model.Address;
import com.example.crud_pessoa.model.Person;
import com.example.crud_pessoa.repository.AdressRepository;
import com.example.crud_pessoa.repository.PersonRepository;
import jakarta.validation.constraints.Null;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.*;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class PersonServiceTest {
    @Mock
    private PersonRepository personRepository;

    @InjectMocks
    private PersonService personService;

    private Person buildPerson(Long id){
        Person p = new Person();
        p.setId(id);
        p.setName("Josias");
        p.setDateOfBirth(LocalDate.of(1994, 9, 24));
        p.setCpf("00110010111");

        Address a1 = new Address();
        a1.setId(1L);
        a1.setStreet("Rua das Laranjeiras");
        a1.setCep("12345-000");
        a1.setNumber(100);
        a1.setCity("São Paulo");
        a1.setState("SP");

        Address a2 = new Address();
        a2.setId(2L);
        a2.setStreet("Av. Brasil");
        a2.setCep("54321-000");
        a2.setNumber(200);
        a2.setCity("Campinas");
        a2.setState("SP");

        p.setAddresses(new ArrayList<>(List.of(a1, a2)));

        return p;
    }

    @Test
    void testListPerson_withResults() {
        List<Person> list = List.of(buildPerson(1L), buildPerson(2L));
        when(personRepository.findAll()).thenReturn(list);

        List<Person> result = personService.listPerson();

        assertEquals(2, result.size());
        assertEquals("Josias", result.get(0).getName());
        verify(personRepository, times(1)).findAll();
    }

    @Test
    void testListPerson_withEmptyList() {
        when(personRepository.findAll()).thenReturn(List.of());

        List<Person> result = personService.listPerson();

        assertTrue(result.isEmpty(), "A lista deve estar vazia");
        verify(personRepository, times(1)).findAll();
    }


    @Test
    void savePerson() {
        Person person = buildPerson(null);
        Person saved = buildPerson(1L);

        when(personRepository.save(person)).thenReturn(saved);

        Person result = personService.savePerson(person);

        assertEquals(1L, result.getId());
        assertEquals("Josias", result.getName());
        verify(personRepository, times(1)).save(person);
    }

    @Test
    void testUpdatePerson_success() {
        Long id = 1L;
        Person existing = buildPerson(id);
        Person updated = buildPerson(id);
        updated.setName("Josias Atualizado");
        updated.setCpf("99999999999");

        Address newAddress = new Address();
        newAddress.setId(3L);
        newAddress.setStreet("Nova Rua");
        newAddress.setCep("11111-000");
        newAddress.setNumber(300);
        newAddress.setCity("Ribeirão Preto");
        newAddress.setState("SP");

        updated.setAddresses(List.of(newAddress));

        when(personRepository.findById(id)).thenReturn(Optional.of(existing));
        when(personRepository.save(any())).thenAnswer(invocation -> invocation.getArgument(0));

        Person result = personService.updatePerson(id, updated);

        assertEquals("Josias Atualizado", result.getName());
        assertEquals("99999999999", result.getCpf());
        assertEquals(1, result.getAddresses().size());
        assertEquals("Nova Rua", result.getAddresses().get(0).getStreet());

        assertEquals(result, result.getAddresses().get(0).getPerson());

        verify(personRepository).findById(id);
        verify(personRepository).save(result);
    }

    @Test
    void testUpdatePerson_notFound() {
        Long id = 99L;
        Person updated = buildPerson(id);

        when(personRepository.findById(id)).thenReturn(Optional.empty());

        assertThrows(RuntimeException.class, () -> personService.updatePerson(id, updated));

        verify(personRepository).findById(id);
        verify(personRepository, never()).save(any());
    }

    @Test
    void testUpdatePerson_emptyAddressList() {
        Long id = 2L;
        Person existing = buildPerson(id);
        Person updated = buildPerson(id);
        updated.setAddresses(List.of()); // vazio

        when(personRepository.findById(id)).thenReturn(Optional.of(existing));
        when(personRepository.save(any())).thenAnswer(invocation -> invocation.getArgument(0));

        Person result = personService.updatePerson(id, updated);

        assertTrue(result.getAddresses().isEmpty());
        verify(personRepository).save(result);
    }

    @Test
    void testUpdatePerson_preserveId() {
        Long id = 3L;
        Person existing = buildPerson(id);
        Person updated = buildPerson(id);
        updated.setName("Novo Nome");

        when(personRepository.findById(id)).thenReturn(Optional.of(existing));
        when(personRepository.save(any())).thenAnswer(invocation -> invocation.getArgument(0));

        Person result = personService.updatePerson(id, updated);

        assertEquals(id, result.getId());
        assertEquals("Novo Nome", result.getName());
    }

    @Test
    void testDeletePerson_success() {
        Long id = 1L;

        when(personRepository.existsById(id)).thenReturn(true);

        personService.deletePerson(id);

        verify(personRepository).deleteById(id);
    }

    @Test
    void testDeletePerson_notFound() {
        Long id = 99L;
        Mockito.when(personRepository.existsById(id)).thenReturn(false); // ID não existe no banco

        RuntimeException exception = assertThrows(RuntimeException.class, () -> {
            personService.deletePerson(id); // Espera-se que uma exceção seja lançada
        });

        assertEquals("Pessoa não encontrada", exception.getMessage());

        Mockito.verify(personRepository, Mockito.never()).deleteById(id);
    }
    @Test
    void testCalculateAge_sameDay() {
        LocalDate birthDate = LocalDate.now();

        int age = personService.calculateAge(birthDate);

        assertEquals(0, age);
    }

    @Test
    void testCalculateAge_inThePast() {
        LocalDate birthDate = LocalDate.now().minusYears(10);

        int age = personService.calculateAge(birthDate);

        assertEquals(10, age);
    }

    @Test
    void testCalculateAge_sameYear() {
        LocalDate birthDate = LocalDate.now().minusMonths(3);

        int age = personService.calculateAge(birthDate);

        assertEquals(0, age);
    }
}