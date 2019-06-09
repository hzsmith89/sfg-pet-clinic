package com.halseyzsmith.sfgpetclinic.services.springdatajpa;

import com.halseyzsmith.sfgpetclinic.model.Vet;
import com.halseyzsmith.sfgpetclinic.repositories.VetRepository;
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
class VetSDJpaServiceTest {

    @Mock
    VetRepository vetRepository;

    @InjectMocks
    VetSDJpaService vetService;

    private final Long VET_ID = 1L;

    private Vet mockVet;

    @BeforeEach
    void setUp() {
        mockVet = Vet.builder().id(VET_ID).build();
    }

    @Test
    void findAll() {
        Set<Vet> mockVets = new HashSet<>();
        mockVets.add(Vet.builder().id(1L).firstName("Bill").build());
        mockVets.add(Vet.builder().id(2L).firstName("Tom").build());

        when(vetRepository.findAll()).thenReturn(mockVets);

        Set<Vet> actualVets = vetService.findAll();

        assertNotNull(actualVets);
        assertEquals(2, actualVets.size());

        verify(vetRepository).findAll();
    }

    @Test
    void findById() {
        when(vetRepository.findById(anyLong())).thenReturn(Optional.of(mockVet));

        Vet actualVet = vetService.findById(VET_ID);

        assertNotNull(actualVet);
        assertEquals(VET_ID, actualVet.getId());

        verify(vetRepository).findById(anyLong());
    }

    @Test
    void findByIdNotFound() {
        when(vetRepository.findById(anyLong())).thenReturn(Optional.empty());

        Vet actualVet = vetService.findById(VET_ID);

        assertNull(actualVet);

        verify(vetRepository).findById(anyLong());
    }

    @Test
    void save() {
        Long id = 2L;
        Vet vet2 = Vet.builder().id(id).build();

        when(vetRepository.save(any())).thenReturn(vet2);

        Vet savedVet = vetService.save(vet2);

        assertNotNull(savedVet);
        assertEquals(id, savedVet.getId());

        verify(vetRepository).save(any());
    }

    @Test
    void delete() {
        vetService.delete(mockVet);

        verify(vetRepository).delete(any());
    }

    @Test
    void deleteById() {
        vetService.deleteById(VET_ID);

        verify(vetRepository).deleteById(anyLong());
    }
}