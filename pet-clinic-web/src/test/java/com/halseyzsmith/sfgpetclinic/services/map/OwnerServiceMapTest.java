package com.halseyzsmith.sfgpetclinic.services.map;

import com.halseyzsmith.sfgpetclinic.model.Owner;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

class OwnerServiceMapTest {

    OwnerMapService ownerMapService;

    final Long ownerId = 1L;
    final String lastName = "Smith";

    @BeforeEach
    void setUp() {
        ownerMapService = new OwnerMapService(new PetTypeMapService(), new PetMapService());

        ownerMapService.save(Owner.builder().id(ownerId).lastName(lastName).build());
    }

    @Test
    void findAll() {
        Set<Owner> owners = ownerMapService.findAll();

        assertEquals(1, owners.size());
    }

    @Test
    void findById() {
        Owner owner = ownerMapService.findById(ownerId);

        assertEquals(ownerId, owner.getId());
    }

    @Test
    void saveExistingId() {
        Long id = 2L;

        Owner owner2 = new Owner().builder().id(id).build();
        Owner savedOwner = ownerMapService.save(owner2);

        assertEquals(id, savedOwner.getId());
    }

    @Test
    void saveNoId() {
        Owner owner  = new Owner();
        Owner savedOwner = ownerMapService.save(owner);

        assertNotNull(savedOwner);
        assertNotNull(savedOwner.getId());
    }

    @Test
    void delete() {
        Owner owner = ownerMapService.findById(ownerId);

        assertNotNull(owner);

        ownerMapService.delete(owner);

        assertNull(ownerMapService.findById(ownerId));
    }

    @Test
    void deleteById() {
        Owner owner = ownerMapService.findById(ownerId);

        assertNotNull(owner);

        ownerMapService.deleteById(owner.getId());

        assertNull(ownerMapService.findById(ownerId));
    }


    @Test
    void findByLastName() {
        Owner owner = ownerMapService.findByLastName(lastName);

        assertNotNull(owner);
        assertEquals(ownerId, owner.getId());
        assertEquals(lastName, owner.getLastName());
    }

    @Test
    void findByLastNameNotFound() {
        Owner owner = ownerMapService.findByLastName("foo");
        assertNull(owner);
    }
}