package com.example.ml_app_consultorio.service;

import com.example.ml_app_consultorio.exception.EntityNotFoundException;
import com.example.ml_app_consultorio.model.Dentist;
import com.example.ml_app_consultorio.model.Diary;
import com.example.ml_app_consultorio.model.Patient;
import com.example.ml_app_consultorio.model.Turn;
import com.example.ml_app_consultorio.repository.DentistRepository;
import com.example.ml_app_consultorio.repository.DiaryRepository;
import com.example.ml_app_consultorio.repository.PatientRepository;
import com.example.ml_app_consultorio.repository.TurnRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.Collator;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ConsultorioService implements IConsultorioService{
    @Autowired
    private DentistRepository dentistRepository;
    @Autowired
    private PatientRepository patientRepository;
    @Autowired
    private DiaryRepository diaryRepository;
    @Autowired
    private TurnRepository turnRepository;

    @Override
    public Dentist getDentist(Long idDentist) throws EntityNotFoundException {
        Dentist dentist= dentistRepository.findById(idDentist).orElse(null);
        if(dentist==null){
            throw new EntityNotFoundException("dentista ID:"+idDentist);
        }
        return dentist;
    }

    @Override
    public List<Dentist> getAllDentists() {
        return dentistRepository.findAll();
    }

    @Override
    public void saveDentist(Dentist dentist) {
        dentistRepository.save(dentist);
    }

    @Override
    public void deleteDentist(Long idDentist) {
        dentistRepository.deleteById(idDentist);
    }

    @Override
    public void editDentist(Long idDentist, String name, String lastName, String address, Long dni, Date birthDate, Long phone, String mail, String codeMP) {

    }

    @Override
    public List<Patient> getAllPatients() {
        return patientRepository.findAll();
    }

    @Override
    public Patient getPatient(Long idPatient) throws EntityNotFoundException {
        Patient patient= patientRepository.findById(idPatient).orElse(null);
        if(patient==null){
            throw new EntityNotFoundException("paciente ID:"+idPatient);
        }
        return patient;
    }

    @Override
    public void savePatient(Patient patient) {
        patientRepository.save(patient);
    }

    @Override
    public void deletePatient(Long idPatient) {
        patientRepository.deleteById(idPatient);
    }

    @Override
    public void editPatient(Long idPatient, String name, String lastName, String address, Long dni, Date birthDate, Long phone, String mail) {

    }

    @Override
    public List<Diary> getAllDiarys() {
        return diaryRepository.findAll();
    }

    @Override
    public Diary getDiary(Long idDiary) throws EntityNotFoundException {
        var diary =diaryRepository.findById(idDiary).orElseThrow(() ->new EntityNotFoundException("No se encontro la agenda ID:" +idDiary));
        return diary;
    }

    @Override
    public void createDiary(Diary diary) {
        diaryRepository.save(diary);
    }

    @Override
    public Turn getTurnOfDiary(Long idDiary, Long idTurn) throws EntityNotFoundException {
        /*Sin repo de turnos
        var turno =  diaryRepository.findById(idDiary)
                .orElseThrow(() ->new EntityNotFoundException("diary ID:" +idDiary))
                .getTurns()
                .stream()
                .filter(t -> t.getIdTurn().equals(idTurn))
                .findFirst()
                .orElseThrow(() ->new EntityNotFoundException("turno ID:" +idTurn));
                */
        Turn turno = turnRepository.findById(idTurn).orElseThrow(()->new EntityNotFoundException("No se pudo hallar el turno ID:"+idTurn));
        if(!turno.getDiary().getIdDiary().equals(idDiary)){
            throw new EntityNotFoundException("El turno ingresado no corresponde con la agenda ID: "+idDiary);
        }
        return turno;
    }

    @Override
    public List<Turn> getAllTurnsByIdDiary(Long idDiary) throws EntityNotFoundException {
        return diaryRepository.findById(idDiary)
                .orElseThrow(() ->new EntityNotFoundException("No se encontraron turnos para la agenda ID:" +idDiary))
                .getTurns();
    }

    @Override
    public Turn getTurnById(Long idTurn) throws EntityNotFoundException {
       /* Sin repo de turnos
        List<Diary> diarys = diaryRepository.findAll();
        for (Diary diary : diarys) {
            var turnTmp = diary.getTurns().stream()
                    .filter(t -> t.getIdTurn().equals(idTurn))
                    .findFirst().orElse(null);
            if(turnTmp!=null){
                turn=turnTmp;
            }
        }
        */

        return turnRepository.findById(idTurn).orElseThrow(()->new EntityNotFoundException("No se encontro turno con el ID:"+idTurn));

    }

    @Override
    public Turn getTurnByIds(Long idDiary, Long idTurn) throws EntityNotFoundException {
        Diary diary= diaryRepository.findById(idDiary).orElseThrow(() -> new EntityNotFoundException("No es posible hallar la agenda ID:"+idDiary));
        var turn =diary.getTurns().stream().filter(t -> t.getIdTurn().equals(idTurn)).findFirst().orElseThrow(()->new EntityNotFoundException("No se encontro el turno ID:"+idTurn));
        return  turn;
    }

    @Override
    public void createTurnIntoDiary(Long idDiary, Turn turn) {
        Diary diary =diaryRepository.findById(idDiary).orElse(null);
        if(diary!=null){
            List<Turn> turns =  diary.getTurns();
            turns.add(turn);
            diary.setTurns(turns);
            diaryRepository.save(diary);
        }
    }

    @Override
    public void deleteDiary(Long idDiary) {
        diaryRepository.deleteById(idDiary);
    }

    @Override
    public void deleteTurn(Long idDiary, Long idTurn) {
        Diary diary =diaryRepository.findById(idDiary).orElse(null);
        if(diary!=null){
            List<Turn> turns =  diary.getTurns().stream()
                    .filter(turn -> !turn.getIdTurn().equals(idTurn))
                    .collect(Collectors.toList());
            diary.setTurns(turns);
            diaryRepository.save(diary);
        }
    }
}
