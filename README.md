Data Ingestion Tool
Overview
This project is a web-based application designed to facilitate data ingestion between a ClickHouse database and a Flat File platform. The application enables:
- Bidirectional data flow:- Exporting data from ClickHouse to Flat File.
- Importing data from Flat File to ClickHouse.

- JWT-based authentication for ClickHouse connections.
- Schema discovery and column selection for ingestion.
- User-friendly interface for configuring connections, previewing data, and monitoring ingestion progress.


Features
- Bidirectional Data Flow:- ClickHouse to Flat File: Export selected columns into a CSV file.
- Flat File to ClickHouse: Import data from a CSV file into ClickHouse.

- Source Selection:- UI allows users to select the source and target (ClickHouse or Flat File).

- Schema Discovery:- Fetch table names from ClickHouse or parse Flat File schemas.

- Column Selection:- Enable users to select specific columns for ingestion.

- Completion Reporting:- Display the total number of records processed upon completion.

- Error Handling:- Handle authentication, connection, query, and ingestion errors gracefully.

- UI Features:- Input fields for connection parameters (host, port, database, etc.).
- Buttons for actions: Connect, Load Columns, Preview, Start Ingestion.
- Status updates and result display.

Technical Details
Backend
- Developed in Java using Spring Boot.
- Utilizes the ClickHouse JDBC Driver for database interactions.
- Efficient batch processing for handling large datasets.

Frontend
- Implemented using HTML/CSS/JavaScript.

Database
- ClickHouse instance (local or cloud-based) for testing.
- MySQL used for storing application configurations.


Setup Instructions
Prerequisites
- Java: Install JDK 17 or later.
- Maven: Install Maven for dependency management.
- MySQL:- Install MySQL Server.
- Create a database named data_ingestion_tool.

- ClickHouse:- Install ClickHouse (local or Docker-based).

- Postman: For testing APIs manually.

Database Setup
- Create the MySQL database:CREATE DATABASE data_ingestion_tool;

- Configure the MySQL database connection in src/main/resources/application.properties:spring.datasource.url=jdbc:mysql://localhost:3306/data_ingestion_tool
spring.datasource.username=<your_mysql_username>
spring.datasource.password=<your_mysql_password>
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true



Build & Run Instructions
Build the Project
- Open the terminal and navigate to the project directory.
- Build the project:mvn clean install


Run the Application
- Start the application:mvn spring-boot:run

- The backend server will start, and you'll see logs indicating the application is running. The application will be accessible at:http://localhost:8080



How to Use
Frontend
Open index.html in a browser (or deploy the frontend along with the backend server).
- Select Data Source:- Choose ClickHouse or Flat File.

- Enter Connection Parameters:- For ClickHouse: Host, Port, Database, Username, JWT Token.
- For Flat File: File Path, Delimiter.

- Fetch Schema:- Click "Fetch Tables" or "Load Columns" to fetch schema from the selected source.

- Select Columns:- Use checkboxes to select specific columns for ingestion.

- Start Ingestion:- Click "Start Ingestion" and monitor the progress via status updates.



API Endpoints
1. Add ClickHouse Config
- Method: POST
- Endpoint: /api/clickhouse/add-config
- Payload:{
    "host": "127.0.0.1",
    "port": 8123,
    "dbName": "test_db",
    "user": "default",
    "jwtToken": "your_jwt_token"
}

- Description: Saves ClickHouse configuration.

2. Fetch Schema
- Method: POST
- Endpoint: /api/clickhouse/fetch-schema
- Payload:{
    "host": "127.0.0.1",
    "port": 8123,
    "dbName": "test_db",
    "user": "default",
    "jwtToken": "your_jwt_token"
}

- Description: Fetches table names from ClickHouse.

3. Export Data
- Method: POST
- Endpoint: /api/clickhouse/export
- Query Parameter: tableName=<table_name>
- Payload:{
    "host": "127.0.0.1",
    "port": 8123,
    "dbName": "test_db",
    "user": "default",
    "jwtToken": "your_jwt_token"
}

- Description: Exports data from ClickHouse to a Flat File.

4. Import Data
- Method: POST
- Endpoint: /api/clickhouse/import
- Query Parameters:- filePath=<path_to_flat_file>
- delimiter=,

- Payload:{
    "host": "127.0.0.1",
    "port": 8123,
    "dbName": "test_db",
    "user": "default",
    "jwtToken": "your_jwt_token"
}

- Description: Imports data from a Flat File to ClickHouse.


Testing Scenarios
- ClickHouse → Flat File:- Test exporting data with selected columns.

- Flat File → ClickHouse:- Test importing a Flat File into ClickHouse.

- Authentication Failures:- Validate handling of invalid JWT tokens.

- Error Handling:- Test connection and query failures.
