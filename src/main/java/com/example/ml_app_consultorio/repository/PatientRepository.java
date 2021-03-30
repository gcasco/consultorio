package com.example.ml_app_consultorio.repository;

import com.example.ml_app_consultorio.model.Dentist;
import com.example.ml_app_consultorio.model.Patient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PatientRepository extends JpaRepository<Patient, Long> {
}
