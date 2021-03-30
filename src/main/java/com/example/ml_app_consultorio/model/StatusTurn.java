package com.example.ml_app_consultorio.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "StatusTurn")
public class StatusTurn {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long idStatusTur;
    private String name;
    private String description;
    @OneToMany(mappedBy = "statusTurn")
    private List<Turn> turns;
}
