package com.halseyzsmith.sfgpetclinic.services.springdatajpa;

import com.halseyzsmith.sfgpetclinic.model.Visit;
import com.halseyzsmith.sfgpetclinic.repositories.VisitRepository;
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
class VisitSDJpaServiceTest {

    @Mock
    VisitRepository visitRepository;

    @InjectMocks
    VisitSDJpaService visitService;

    private final Long VISIT_ID = 1L;

    private Visit mockVisit;

    @BeforeEach
    void setUp() {
        mockVisit = Visit.builder().id(VISIT_ID).build();
    }

    @Test
    void findAll() {
        Set<Visit> mockVisits = new HashSet<>();
        mockVisits.add(Visit.builder().id(1L).build());
        mockVisits.add(Visit.builder().id(2L).build());

        when(visitRepository.findAll()).thenReturn(mockVisits);

        Set<Visit> actualVisits = visitService.findAll();

        assertNotNull(actualVisits);
        assertEquals(2, actualVisits.size());

        verify(visitRepository).findAll();
    }

    @Test
    void findById() {
        when(visitRepository.findById(anyLong())).thenReturn(Optional.of(mockVisit));

        Visit actualVisit = visitService.findById(VISIT_ID);

        assertNotNull(actualVisit);
        assertEquals(VISIT_ID, actualVisit.getId());

        verify(visitRepository).findById(anyLong());
    }

    @Test
    void findByIdNotFound() {
        when(visitRepository.findById(anyLong())).thenReturn(Optional.empty());

        Visit actualVisit = visitService.findById(VISIT_ID);

        assertNull(actualVisit);

        verify(visitRepository).findById(anyLong());
    }

    @Test
    void save() {
        Long id = 2L;
        Visit visit2 = Visit.builder().id(id).build();

        when(visitRepository.save(any())).thenReturn(visit2);

        Visit savedVisit = visitService.save(visit2);

        assertNotNull(savedVisit);
        assertEquals(id, savedVisit.getId());

        verify(visitRepository).save(any());
    }

    @Test
    void delete() {
        visitService.delete(mockVisit);

        verify(visitRepository).delete(any());
    }

    @Test
    void deleteById() {
        visitService.deleteById(VISIT_ID);

        verify(visitRepository).deleteById(anyLong());
    }
}