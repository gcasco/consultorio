package com.example.ml_app_consultorio.repository;

import com.example.ml_app_consultorio.model.Diary;
import com.example.ml_app_consultorio.model.Turn;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TurnRepository extends JpaRepository<Turn, Long> {
}
