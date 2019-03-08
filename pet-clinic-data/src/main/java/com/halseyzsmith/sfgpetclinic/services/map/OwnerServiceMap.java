package com.halseyzsmith.sfgpetclinic.services.map;

import com.halseyzsmith.sfgpetclinic.model.Owner;
import com.halseyzsmith.sfgpetclinic.model.Pet;
import com.halseyzsmith.sfgpetclinic.services.OwnerService;
import com.halseyzsmith.sfgpetclinic.services.PetService;
import com.halseyzsmith.sfgpetclinic.services.PetTypeService;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
@AllArgsConstructor
@Profile({"default", "map"})
public class OwnerServiceMap extends AbstractServiceMap<Owner, Long> implements OwnerService {

    private final PetTypeService petTypeService;
    private final PetService petService;

    @Override
    public Set<Owner> findAll() {
        return super.findAll();
    }

    @Override
    public Owner findById(Long id) {
        return super.findById(id);
    }

    @Override
    public Owner save(Owner object) {

        if (object != null) {
            if (object.getPets() != null) {
                object.getPets().forEach( pet -> {
                    if (pet.getPetType() != null) {
                        if (pet.getPetType().getId() == null) {
                            pet.setPetType(petTypeService.save(pet.getPetType()));
                        }
                    } else {
                        throw new RuntimeException("Pet type is required!");
                    }
                    if (pet.getId() == null) {
                        Pet savedPet = petService.save(pet);
                        pet.setId(savedPet.getId());
                    }
                });
            }

            return super.save(object);
        } else {
            return null;
        }
    }

    @Override
    public void deleteById(Long id) {
        super.deleteById(id);
    }

    @Override
    public void delete(Owner object) {
        super.delete(object);
    }

    @Override
    public Owner findByLastName(String lastName) {
        return null;
    }
}
