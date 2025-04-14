package com.example.Data_Ingestion_Tool.Model;

import jakarta.persistence.*;

@Entity
@Table(name = "flatfile_config")
public class FlatFileConfig {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String fileName;
    private String delimiter;

    // Getters and Setters
    public String getFileName() { return fileName; }
    public void setFileName(String fileName) { this.fileName = fileName; }

    public String getDelimiter() { return delimiter; }
    public void setDelimiter(String delimiter) { this.delimiter = delimiter; }
}