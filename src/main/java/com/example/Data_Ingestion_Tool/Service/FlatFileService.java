package com.example.Data_Ingestion_Tool.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.Data_Ingestion_Tool.Model.FlatFileConfig;
import com.example.Data_Ingestion_Tool.Repository.FlatFileConfigRepository;

import java.io.BufferedReader;
import java.io.FileReader;

@Service
public class FlatFileService {

    @Autowired
    private FlatFileConfigRepository flatFileConfigRepository;

    public void saveFlatFileConfig(FlatFileConfig flatFileConfig) {
        flatFileConfigRepository.save(flatFileConfig);
    }

    public String previewFlatFile(String filePath, String delimiter) {
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            StringBuilder preview = new StringBuilder("Preview:\n");
            int count = 0;
            while ((line = reader.readLine()) != null && count < 100) {
                preview.append(line.replace(delimiter, "|")).append("\n");
                count++;
            }
            return preview.toString();
        } catch (Exception e) {
            throw new RuntimeException("Error previewing flat file: " + e.getMessage());
        }
    }
}