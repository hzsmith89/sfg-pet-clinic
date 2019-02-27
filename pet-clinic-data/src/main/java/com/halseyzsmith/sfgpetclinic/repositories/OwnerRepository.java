package com.halseyzsmith.sfgpetclinic.repositories;

import com.halseyzsmith.sfgpetclinic.model.Owner;
import org.springframework.data.repository.CrudRepository;

import java.util.Set;

public interface OwnerRepository extends CrudRepository<Owner, Long> {

    Owner findByLastName(String lastName);
}
