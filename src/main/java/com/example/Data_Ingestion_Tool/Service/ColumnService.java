package com.example.Data_Ingestion_Tool.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.Data_Ingestion_Tool.Model.Column;
import com.example.Data_Ingestion_Tool.Repository.ColumnRepository;

@Service
public class ColumnService {

    @Autowired
    private ColumnRepository columnRepository;

    public void saveColumn(Column column) {
        columnRepository.save(column);
    }

    public String listColumns() {
        StringBuilder columns = new StringBuilder("Columns:\n");
        columnRepository.findAll().forEach(column -> columns.append(column.getName()).append("\n"));
        return columns.toString();
    }
}