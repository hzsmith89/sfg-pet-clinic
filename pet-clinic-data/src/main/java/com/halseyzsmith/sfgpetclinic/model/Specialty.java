package com.halseyzsmith.sfgpetclinic.model;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "specialties")
public class Specialty  extends BaseEntity{

    @Column(name = "description")
    private String description;
}
