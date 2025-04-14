package com.example.Data_Ingestion_Tool.Model;

import jakarta.persistence.*;
import jakarta.persistence.Column;

@Entity
@Table(name = "clickhouse_config")
public class ClickHouseConfig {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String host;
    private int port;
    
    @Column(name = "db_name") // Rename the column to avoid conflicts
    private String dbName;

    private String user;
    private String jwtToken;

    // Getters and Setters
    public String getHost() { return host; }
    public void setHost(String host) { this.host = host; }

    public int getPort() { return port; }
    public void setPort(int port) { this.port = port; }

    public String getDbName() { return dbName; }
    public void setDbName(String dbName) { this.dbName = dbName; }

    public String getUser() { return user; }
    public void setUser(String user) { this.user = user; }

    public String getJwtToken() { return jwtToken; }
    public void setJwtToken(String jwtToken) { this.jwtToken = jwtToken; }

    public String getDatabase() { return dbName; }
    public void setDatabase(String dbName) { this.dbName = dbName; }
}