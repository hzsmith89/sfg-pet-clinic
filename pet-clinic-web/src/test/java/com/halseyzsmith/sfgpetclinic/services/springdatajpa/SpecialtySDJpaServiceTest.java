package com.halseyzsmith.sfgpetclinic.services.springdatajpa;

import com.halseyzsmith.sfgpetclinic.model.Specialty;
import com.halseyzsmith.sfgpetclinic.repositories.SpecialtyRepository;
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
class SpecialtySDJpaServiceTest {
    
    @Mock
    SpecialtyRepository specialtyRepository;

    @InjectMocks
    SpecialtySDJpaService specialtyService;

    private final Long SPECIALTY_ID = 1L;

    private Specialty mockSpecialty;

    @BeforeEach
    void setUp() {
        mockSpecialty = Specialty.builder().id(SPECIALTY_ID).build();
    }

    @Test
    void findAll() {
        Set<Specialty> mockSpecialties = new HashSet<>();
        mockSpecialties.add(Specialty.builder().id(1L).build());
        mockSpecialties.add(Specialty.builder().id(2L).build());

        when(specialtyRepository.findAll()).thenReturn(mockSpecialties);

        Set<Specialty> actualSpecialties = specialtyService.findAll();

        assertNotNull(actualSpecialties);
        assertEquals(2, actualSpecialties.size());

        verify(specialtyRepository).findAll();
    }

    @Test
    void findById() {
        when(specialtyRepository.findById(anyLong())).thenReturn(Optional.of(mockSpecialty));

        Specialty actualSpecialty = specialtyService.findById(SPECIALTY_ID);

        assertNotNull(actualSpecialty);
        assertEquals(SPECIALTY_ID, actualSpecialty.getId());

        verify(specialtyRepository).findById(anyLong());
    }

    @Test
    void findByIdNotFound() {
        when(specialtyRepository.findById(anyLong())).thenReturn(Optional.empty());

        Specialty actualSpecialty = specialtyService.findById(SPECIALTY_ID);

        assertNull(actualSpecialty);

        verify(specialtyRepository).findById(anyLong());
    }

    @Test
    void save() {
        Long id = 2L;
        Specialty specialty2 = Specialty.builder().id(id).build();

        when(specialtyRepository.save(any())).thenReturn(specialty2);

        Specialty savedSpecialty = specialtyService.save(specialty2);

        assertNotNull(savedSpecialty);
        assertEquals(id, savedSpecialty.getId());

        verify(specialtyRepository).save(any());
    }

    @Test
    void delete() {
        specialtyService.delete(mockSpecialty);

        verify(specialtyRepository).delete(any());
    }

    @Test
    void deleteById() {
        specialtyService.deleteById(SPECIALTY_ID);

        verify(specialtyRepository).deleteById(anyLong());
    }
}