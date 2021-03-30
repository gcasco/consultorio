package com.example.ml_app_consultorio.repository;

import com.example.ml_app_consultorio.model.Diary;
import com.example.ml_app_consultorio.model.Patient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DiaryRepository extends JpaRepository<Diary, Long> {
}
