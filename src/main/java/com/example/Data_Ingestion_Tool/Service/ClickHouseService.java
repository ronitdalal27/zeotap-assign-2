package com.example.Data_Ingestion_Tool.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.Data_Ingestion_Tool.Model.ClickHouseConfig;
import com.example.Data_Ingestion_Tool.Repository.ClickHouseConfigRepository;

import java.io.BufferedReader;
import java.io.FileReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

@Service
public class ClickHouseService {

    @Autowired
    private ClickHouseConfigRepository clickHouseConfigRepository;


    @SuppressWarnings("resource")
    public String importFlatFileData(ClickHouseConfig config, String filePath, String delimiter) {
        try {
            // Establish connection to ClickHouse
            Connection connection = connectToClickHouse(config);
            if (connection == null) {
                throw new RuntimeException("Failed to connect to ClickHouse. Connection is null.");
            }

            // Read the Flat File
            BufferedReader reader = new BufferedReader(new FileReader(filePath));
            String header = reader.readLine(); // First line is the header
            if (header == null) {
                throw new RuntimeException("Flat File is empty.");
            }

            // Extract columns from the header
            String[] columns = header.split(delimiter);
            String insertQuery = buildInsertQuery(config.getDbName(), "imported_table", columns);

            PreparedStatement preparedStatement = connection.prepareStatement(insertQuery);
            int batchSize = 1000;
            int count = 0;

            // Parse the remaining lines of the file
            String line;
            while ((line = reader.readLine()) != null) {
                String[] values = line.split(delimiter);
                for (int i = 0; i < values.length; i++) {
                    preparedStatement.setString(i + 1, values[i]); // Set each value into the query
                }
                preparedStatement.addBatch();

                // Execute batch when the batch size is reached
                if (++count % batchSize == 0) {
                    preparedStatement.executeBatch();
                }
            }

            // Execute any remaining batch
            preparedStatement.executeBatch();
            reader.close();
            connection.close();

            return "Data imported successfully! Total records: " + count;
        } catch (Exception e) {
            throw new RuntimeException("Error importing data: " + e.getMessage(), e);
        }
    }

private String buildInsertQuery(String database, String table, String[] columns) {
    StringBuilder query = new StringBuilder("INSERT INTO " + database + "." + table + " (");
    for (String column : columns) {
        query.append(column).append(",");
    }
    query.deleteCharAt(query.length() - 1); // Remove last comma
    query.append(") VALUES (");
    for (int i = 0; i < columns.length; i++) {
        query.append("?,"); // Use placeholders for parameterized query
    }
    query.deleteCharAt(query.length() - 1); // Remove last comma
    query.append(")");
    return query.toString();
}

    public void saveClickHouseConfig(ClickHouseConfig config) {
        clickHouseConfigRepository.save(config);
    }

    public String fetchSchema(ClickHouseConfig config) {
        try {
            Connection connection = connectToClickHouse(config);
            
            if (connection == null) {
                throw new RuntimeException("Failed to connect to ClickHouse. Connection is null.");
            }
    
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT name FROM system.tables WHERE database = '" + config.getDbName() + "'");
            StringBuilder schema = new StringBuilder("Tables:\n");
    
            while (resultSet.next()) {
                schema.append(resultSet.getString("name")).append("\n");
            }
    
            connection.close();
            return schema.toString();
        } catch (Exception e) {
            System.err.println("Error in fetchSchema: " + e.getMessage());
            e.printStackTrace();
            throw new RuntimeException("Error fetching schema: " + e.getMessage(), e);
        }
    }

    public String exportDataToFlatFile(ClickHouseConfig config, String tableName) {
        try {
            Connection connection = connectToClickHouse(config);
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM " + tableName);

            // Example logic for writing ResultSet to flat file
            StringBuilder data = new StringBuilder();
            int columnCount = resultSet.getMetaData().getColumnCount();
            while (resultSet.next()) {
                for (int i = 1; i <= columnCount; i++) {
                    data.append(resultSet.getString(i)).append(i < columnCount ? "," : "\n");
                }
            }

            connection.close();
            return "Data exported successfully!";
        } catch (Exception e) {
            throw new RuntimeException("Error exporting data: " + e.getMessage());
        }
    }

    private Connection connectToClickHouse(ClickHouseConfig config) throws Exception {
        String jdbcUrl = "jdbc:clickhouse://" + config.getHost() + ":" + config.getPort() + "/" + config.getDbName();
        Properties properties = new Properties();
        properties.put("user", config.getUser());
        properties.put("password", config.getJwtToken());
    
        System.out.println("Connecting to ClickHouse with URL: " + jdbcUrl);
        System.out.println("User: " + config.getUser());
        System.out.println("Password: " + config.getJwtToken());
    
        try {
            Class.forName("com.clickhouse.jdbc.ClickHouseDriver");
            Connection connection = DriverManager.getConnection(jdbcUrl, properties);
            System.out.println("Successfully connected to ClickHouse.");
            return connection;
        } catch (SQLException e) {
            System.err.println("Failed to connect to ClickHouse: " + e.getMessage());
            throw e;
        }
    }
}