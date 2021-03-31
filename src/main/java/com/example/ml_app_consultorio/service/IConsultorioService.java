package com.example.ml_app_consultorio.service;

import com.example.ml_app_consultorio.exception.EntityNotFoundException;
import com.example.ml_app_consultorio.model.Dentist;
import com.example.ml_app_consultorio.model.Diary;
import com.example.ml_app_consultorio.model.Patient;
import com.example.ml_app_consultorio.model.Turn;

import java.util.Date;
import java.util.List;

public interface IConsultorioService {

    Dentist getDentist(Long idDentist) throws EntityNotFoundException;
    List<Dentist> getAllDentists();
    void saveDentist(Dentist dentist);
    void deleteDentist(Long idDentist);
    void editDentist(Long idDentist, String name, String lastName, String address, Long dni, Date birthDate, Long phone, String mail, String codeMP);

    void addDiaryToDentist(Diary diary, Long idDentist) throws EntityNotFoundException;

    void addDiaryListToDentist(List<Diary> diarys, Long idDentist);

    List<Patient> getAllPatients();
    Patient getPatient(Long idPatient) throws EntityNotFoundException;
    void savePatient(Patient patient);
    void deletePatient(Long idPatient);
    void editPatient(Long idPatient, String name, String lastName, String address, Long dni, Date birthDate, Long phone, String mail);

    List<Diary> getAllDiarys();
    Diary getDiary(Long idDiary) throws EntityNotFoundException;
    void createDiary(Diary diary);
    Turn getTurnOfDiary(Long idDiary, Long idTurn) throws EntityNotFoundException;
    List<Turn> getAllTurnsByIdDiary(Long idDiary) throws EntityNotFoundException;
    Turn getTurnById(Long idTurn) throws EntityNotFoundException;
    Turn getTurnByIds(Long idDiary,Long idTurn) throws EntityNotFoundException;
    void createTurnIntoDiary(Long idDiary, Turn turn);
    void deleteDiary(Long idDiary);
    void deleteTurn(Long idDiary, Long idTurn);

}
