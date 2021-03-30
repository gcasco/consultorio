package com.example.ml_app_consultorio.controller;

import com.example.ml_app_consultorio.exception.EntityNotFoundException;
import com.example.ml_app_consultorio.exception.GenericException;
import com.example.ml_app_consultorio.model.Dentist;
import com.example.ml_app_consultorio.model.Diary;
import com.example.ml_app_consultorio.model.Patient;
import com.example.ml_app_consultorio.model.Turn;
import com.example.ml_app_consultorio.service.IConsultorioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
public class ConsultorioController {
    @Autowired
    private IConsultorioService service;

    /*Dentista*/
    @GetMapping("/dentists")
    public List<Dentist> getDentistsList(){
        List<Dentist> dentists = service.getAllDentists();
        return dentists;
    }

    @PostMapping(value="/dentist/create")
    public String createDentist(@RequestBody Dentist dentist ) {
        service.saveDentist(dentist);
        return "El dentista fue guardado correctamente";
    }

    @PostMapping(value="/dentist/delete/{id}")
    public String deleteDentist(@PathVariable long id )  {
        service.deleteDentist(id);

        return "El dentista fue eliminado correctamente";
    }

    @PostMapping(value="/dentist/edit/{id}")
    public String editDentist(@PathVariable long id,@RequestParam ("name") String newName,
                              @RequestParam ("lastName") String newLastName,
                              @RequestParam ("address") String address,
                              @RequestParam ("dni") Long newDni,
                              @RequestParam ("birthDate") Date birthDate,
                              @RequestParam ("phone") Long phone,
                              @RequestParam ("mail") String mail,
                              @RequestParam ("codeMP") String codeMP) {
        service.editDentist(id, newName, newLastName, address,  newDni, birthDate, phone, mail, codeMP);
        return "El dentista fue editado correctamente";
    }
    /*Fin Dentista*/

    /*Paciente*/
    @GetMapping("/patients")
    public List<Patient> getPatientsList(){
        List<Patient> patients = service.getAllPatients();
        return patients;
    }

    @PostMapping(value="/patient/create")
    public String createPatient(@RequestBody Patient patient ) {
        service.savePatient(patient);
        return "El paciente fue cargado correctamente";
    }

    @PostMapping(value="/patient/delete/{id}")
    public String deletePatient(@PathVariable long id )  {
        service.deletePatient(id);
        return "El paciente fue eliminado correctamente";
    }

    @PostMapping(value="/patient/edit/{id}")
    public String editPatient(@PathVariable long id,@RequestParam ("name") String newName,
                              @RequestParam ("lastName") String newLastName,
                              @RequestParam ("address") String address,
                              @RequestParam ("dni") Long newDni,
                              @RequestParam ("birthDate") Date birthDate,
                              @RequestParam ("phone") Long phone,
                              @RequestParam ("mail") String mail) {
        service.editPatient(id, newName, newLastName, address,  newDni, birthDate, phone, mail);
        return "El paciente fue actualizado correctamente";
    }
    /*Fin Paciente*/


    /*Agenda*/
    @GetMapping("/diarys")
    public List<Diary> getDiarysList(){
        List<Diary> diarys = service.getAllDiarys();
        return diarys;
    }

    @GetMapping("/diarys/{id}")
    public Diary getDiaryById(@PathVariable long id) throws EntityNotFoundException {
        return service.getDiary(id);
    }

    @GetMapping("/diarys/{id}/turns")
    public List<Turn> getTurnsByIdDiary(@PathVariable long id) throws EntityNotFoundException {
        return service.getAllTurnsByIdDiary(id);
    }

    @PostMapping(value="/diary/create")
    public String createDiary(@RequestBody Diary diary ) {
        service.createDiary(diary);
        return "La agenda fue cargado correctamente";
    }
    /*Fin Agenda*/

    @ExceptionHandler(GenericException.class)
    public ResponseEntity<String> handleException(GenericException ex){
        return  new ResponseEntity<String>(ex.getMessage(), HttpStatus.valueOf(ex.getCode()));
    }

}
