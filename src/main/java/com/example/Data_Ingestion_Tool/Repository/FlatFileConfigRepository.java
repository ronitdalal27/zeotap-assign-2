package com.example.Data_Ingestion_Tool.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.Data_Ingestion_Tool.Model.FlatFileConfig;

@Repository
public interface FlatFileConfigRepository extends JpaRepository<FlatFileConfig, Long> { }