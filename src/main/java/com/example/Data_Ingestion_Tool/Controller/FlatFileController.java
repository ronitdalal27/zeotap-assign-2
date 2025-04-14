package com.example.Data_Ingestion_Tool.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.example.Data_Ingestion_Tool.Model.FlatFileConfig;
import com.example.Data_Ingestion_Tool.Service.FlatFileService;

@RestController
@RequestMapping("/api/flatfile")
public class FlatFileController {

    @Autowired
    private FlatFileService flatFileService;

    @PostMapping("/add-config")
    public String addFlatFileConfig(@RequestBody FlatFileConfig config) {
        flatFileService.saveFlatFileConfig(config);
        return "Flat file configuration added successfully!";
    }

    @PostMapping("/preview")
    public String previewFlatFile(@RequestParam String filePath, @RequestParam String delimiter) {
        return flatFileService.previewFlatFile(filePath, delimiter);
    }
}