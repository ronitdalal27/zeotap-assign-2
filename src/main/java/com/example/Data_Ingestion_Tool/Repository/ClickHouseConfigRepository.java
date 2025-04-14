package com.example.Data_Ingestion_Tool.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.Data_Ingestion_Tool.Model.ClickHouseConfig;

@Repository
public interface ClickHouseConfigRepository extends JpaRepository<ClickHouseConfig, Long> { }