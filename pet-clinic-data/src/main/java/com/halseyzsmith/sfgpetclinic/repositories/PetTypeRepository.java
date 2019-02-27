package com.halseyzsmith.sfgpetclinic.repositories;

import com.halseyzsmith.sfgpetclinic.model.PetType;
import org.springframework.data.repository.CrudRepository;

public interface PetTypeRepository extends CrudRepository<PetType, Long> {
}
