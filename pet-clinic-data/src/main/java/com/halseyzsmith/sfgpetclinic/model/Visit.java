package com.halseyzsmith.sfgpetclinic.model;

import lombok.Data;

import java.time.LocalDate;

@Data
public class Visit extends BaseEntity  {
    private LocalDate date;
    private String description;
    private Pet pet;
}
