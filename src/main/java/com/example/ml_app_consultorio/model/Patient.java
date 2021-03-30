package com.example.ml_app_consultorio.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Email;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "patients")
public class Patient {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long idPatient;
    private String name;
    private String lastName;
    private String address;
    private Long dni;
    private Date birthDate;
    private Long phone;
    @Email
    private String mail;
    @OneToMany(mappedBy = "patient")
    private List<Turn> turns;
}
