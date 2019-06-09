package com.halseyzsmith.sfgpetclinic.services.springdatajpa;

import com.halseyzsmith.sfgpetclinic.model.PetType;
import com.halseyzsmith.sfgpetclinic.repositories.PetTypeRepository;
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
class PetTypeSDJpaServiceTest {

    @Mock
    PetTypeRepository petTypeRepository;

    @InjectMocks
    PetTypeSDJpaService petTypeService;

    private final Long PetType_ID = 1L;

    private PetType mockPetType;

    @BeforeEach
    void setUp() {
        mockPetType = PetType.builder().id(PetType_ID).build();
    }

    @Test
    void findAll() {
        Set<PetType> mockPetTypes = new HashSet<>();
        mockPetTypes.add(PetType.builder().id(1L).build());
        mockPetTypes.add(PetType.builder().id(2L).build());

        when(petTypeRepository.findAll()).thenReturn(mockPetTypes);

        Set<PetType> actualPetTypes = petTypeService.findAll();

        assertNotNull(actualPetTypes);
        assertEquals(2, actualPetTypes.size());

        verify(petTypeRepository).findAll();
    }

    @Test
    void findById() {
        when(petTypeRepository.findById(anyLong())).thenReturn(Optional.of(mockPetType));

        PetType actualPetType = petTypeService.findById(PetType_ID);

        assertNotNull(actualPetType);
        assertEquals(PetType_ID, actualPetType.getId());

        verify(petTypeRepository).findById(anyLong());
    }

    @Test
    void findByIdNotFound() {
        when(petTypeRepository.findById(anyLong())).thenReturn(Optional.empty());

        PetType actualPetType = petTypeService.findById(PetType_ID);

        assertNull(actualPetType);

        verify(petTypeRepository).findById(anyLong());
    }

    @Test
    void save() {
        Long id = 2L;
        PetType petType2 = PetType.builder().id(id).build();

        when(petTypeRepository.save(any())).thenReturn(petType2);

        PetType savedPetType = petTypeService.save(petType2);

        assertNotNull(savedPetType);
        assertEquals(id, savedPetType.getId());

        verify(petTypeRepository).save(any());
    }

    @Test
    void delete() {
        petTypeService.delete(mockPetType);

        verify(petTypeRepository).delete(any());
    }

    @Test
    void deleteById() {
        petTypeService.deleteById(PetType_ID);

        verify(petTypeRepository).deleteById(anyLong());
    }

}