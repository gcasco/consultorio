package com.example.ml_app_consultorio.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "turns")
public class Turn {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long idTurn;
    private Date day;
    @ManyToOne
    private StatusTurn statusTurn;
    @ManyToOne
    private Diary diary;
    @ManyToOne
    private Patient patient;
}
