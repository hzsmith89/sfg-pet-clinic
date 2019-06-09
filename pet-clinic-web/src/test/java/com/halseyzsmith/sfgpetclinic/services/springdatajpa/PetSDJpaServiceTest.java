package com.halseyzsmith.sfgpetclinic.services.springdatajpa;

import com.halseyzsmith.sfgpetclinic.model.Pet;
import com.halseyzsmith.sfgpetclinic.repositories.PetRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class PetSDJpaServiceTest {

    @Mock
    PetRepository petRepository;

    @InjectMocks
    PetSDJpaService petService;

    private final Long PET_ID = 1L;

    private Pet mockPet;

    @BeforeEach
    void setUp() {
        mockPet = Pet.builder().id(PET_ID).build();
    }

    @Test
    void findAll() {
        Set<Pet> mockPets = new HashSet<>();
        mockPets.add(Pet.builder().id(1L).build());
        mockPets.add(Pet.builder().id(2L).build());

        when(petRepository.findAll()).thenReturn(mockPets);

        Set<Pet> actualPets = petService.findAll();

        assertNotNull(actualPets);
        assertEquals(2, actualPets.size());

        verify(petRepository).findAll();
    }

    @Test
    void findById() {
        when(petRepository.findById(anyLong())).thenReturn(Optional.of(mockPet));

        Pet actualPet = petService.findById(PET_ID);

        assertNotNull(actualPet);
        assertEquals(PET_ID, actualPet.getId());

        verify(petRepository).findById(anyLong());
    }

    @Test
    void findByIdNotFound() {
        when(petRepository.findById(anyLong())).thenReturn(Optional.empty());

        Pet actualPet = petService.findById(PET_ID);

        assertNull(actualPet);

        verify(petRepository).findById(anyLong());
    }

    @Test
    void save() {
        Long id = 2L;
        Pet pet2 = Pet.builder().id(id).build();

        when(petRepository.save(any())).thenReturn(pet2);

        Pet savedPet = petService.save(pet2);

        assertNotNull(savedPet);
        assertEquals(id, savedPet.getId());

        verify(petRepository).save(any());
    }

    @Test
    void delete() {
        petService.delete(mockPet);

        verify(petRepository).delete(any());
    }

    @Test
    void deleteById() {
        petService.deleteById(PET_ID);

        verify(petRepository).deleteById(anyLong());
    }
}