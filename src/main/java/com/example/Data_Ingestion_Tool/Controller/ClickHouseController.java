package com.example.Data_Ingestion_Tool.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.Data_Ingestion_Tool.Model.ClickHouseConfig;
import com.example.Data_Ingestion_Tool.Service.ClickHouseService;

@RestController
@RequestMapping("/api/clickhouse")
public class ClickHouseController {

    @Autowired
    private ClickHouseService clickHouseService;

    @PostMapping("/add-config")
    public ResponseEntity<String> addClickHouseConfig(@RequestBody ClickHouseConfig config) {
        try {
            clickHouseService.saveClickHouseConfig(config);
            return ResponseEntity.ok("ClickHouse configuration added successfully!");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to add configuration.");
        }
    }

    @PostMapping("/fetch-schema")
    public String fetchSchema(@RequestBody ClickHouseConfig config) {
        try {
            return clickHouseService.fetchSchema(config);
        } catch (Exception e) {
            System.err.println("Error in fetchSchema: " + e.getMessage());
            e.printStackTrace();
            return "Failed to fetch schema. Please check ClickHouse configuration.";
        }
    }

    @PostMapping("/export")
    public String exportData(@RequestParam String tableName, @RequestBody ClickHouseConfig config) {
        return clickHouseService.exportDataToFlatFile(config, tableName);
    }

    @PostMapping("/import")
    public String importData(@RequestParam String filePath, @RequestParam String delimiter, @RequestBody ClickHouseConfig config) {
        return clickHouseService.importFlatFileData(config, filePath, delimiter);
    }
}