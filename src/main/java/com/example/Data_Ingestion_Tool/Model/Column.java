package com.example.Data_Ingestion_Tool.Model;

import jakarta.persistence.*;

@Entity
@Table(name = "columns")
public class Column {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String type;

    // Constructor
    public Column(String name, String type) {
        this.name = name;
        this.type = type;
    }

    // Getters and Setters
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getType() { return type; }
    public void setType(String type) { this.type = type; }
}