package com.halseyzsmith.sfgpetclinic.repositories;

import com.halseyzsmith.sfgpetclinic.model.Pet;
import org.springframework.data.repository.CrudRepository;

public interface PetRepository extends CrudRepository<Pet, Long> {
}
