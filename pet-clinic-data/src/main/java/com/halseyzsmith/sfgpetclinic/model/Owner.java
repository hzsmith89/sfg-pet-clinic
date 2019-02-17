package com.halseyzsmith.sfgpetclinic.model;

import lombok.Data;

import java.util.Set;

@Data
public class Owner extends Person {

    private String address;
    private String city;
    private String telephone;
    private Set<Pet> pets;
}
