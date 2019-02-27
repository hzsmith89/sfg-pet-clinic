package com.halseyzsmith.sfgpetclinic.repositories;

import com.halseyzsmith.sfgpetclinic.model.Owner;
import org.springframework.data.repository.CrudRepository;

public interface OwnerRepository extends CrudRepository<Owner, Long> {
}
