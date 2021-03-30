package com.example.ml_app_consultorio.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "diarys")
public class Diary {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long idDiary;
    private Date startTime;
    private Date endingTime;

    @ManyToOne
    private Dentist dentist;
    @OneToMany(mappedBy = "diary")
    private List<Turn> turns;
}
