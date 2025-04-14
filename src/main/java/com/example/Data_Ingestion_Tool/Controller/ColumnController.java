package com.example.Data_Ingestion_Tool.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.example.Data_Ingestion_Tool.Model.Column;
import com.example.Data_Ingestion_Tool.Service.ColumnService;

@RestController
@RequestMapping("/api/column")
public class ColumnController {

    @Autowired
    private ColumnService columnService;

    @PostMapping("/add")
    public String addColumn(@RequestBody Column column) {
        columnService.saveColumn(column);
        return "Column added successfully!";
    }

    @GetMapping("/list")
    public String listColumns() {
        return columnService.listColumns();
    }
}