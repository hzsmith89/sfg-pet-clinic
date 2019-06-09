package com.halseyzsmith.sfgpetclinic.services.springdatajpa;

import com.halseyzsmith.sfgpetclinic.model.Owner;
import com.halseyzsmith.sfgpetclinic.repositories.OwnerRepository;
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
class OwnerSDJpaServiceTest {

    @Mock
    OwnerRepository ownerRepository;

    @InjectMocks
    OwnerSDJpaService ownerService;

    private final Long OWNER_ID = 1L;
    private final String LAST_NAME = "Smith";

    private Owner mockOwner;

    @BeforeEach
    void setUp() {
        mockOwner = Owner.builder().id(OWNER_ID).lastName(LAST_NAME).build();
    }

    @Test
    void findByLastName() {
        when(ownerRepository.findByLastName(any())).thenReturn(mockOwner);

        Owner actualOwner = ownerService.findByLastName(LAST_NAME);

        assertNotNull(actualOwner);
        assertEquals(OWNER_ID, actualOwner.getId());
        assertEquals(LAST_NAME, actualOwner.getLastName());

        verify(ownerRepository).findByLastName(any());
    }

    @Test
    void findAll() {
        Set<Owner> mockOwners = new HashSet<>();
        mockOwners.add(Owner.builder().id(1L).firstName("Bill").build());
        mockOwners.add(Owner.builder().id(2L).firstName("Tom").build());

        when(ownerRepository.findAll()).thenReturn(mockOwners);

        Set<Owner> actualOwners = ownerService.findAll();

        assertNotNull(actualOwners);
        assertEquals(2, actualOwners.size());

        verify(ownerRepository).findAll();
    }

    @Test
    void findById() {
        when(ownerRepository.findById(anyLong())).thenReturn(Optional.of(mockOwner));

        Owner actualOwner = ownerService.findById(OWNER_ID);

        assertNotNull(actualOwner);
        assertEquals(OWNER_ID, actualOwner.getId());

        verify(ownerRepository).findById(anyLong());
    }

    @Test
    void findByIdNotFound() {
        when(ownerRepository.findById(anyLong())).thenReturn(Optional.empty());

        Owner actualOwner = ownerService.findById(OWNER_ID);

        assertNull(actualOwner);

        verify(ownerRepository).findById(anyLong());
    }

    @Test
    void save() {
        Long id = 2L;
        Owner owner2 = Owner.builder().id(id).build();

        when(ownerRepository.save(any())).thenReturn(owner2);

        Owner savedOwner = ownerService.save(owner2);

        assertNotNull(savedOwner);
        assertEquals(id, savedOwner.getId());

        verify(ownerRepository).save(any());
    }

    @Test
    void delete() {
        ownerService.delete(mockOwner);

        verify(ownerRepository).delete(any());
    }

    @Test
    void deleteById() {
        ownerService.deleteById(OWNER_ID);

        verify(ownerRepository).deleteById(anyLong());
    }
}