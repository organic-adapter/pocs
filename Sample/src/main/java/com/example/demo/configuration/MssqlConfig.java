package com.example.demo.configuration;

public class MssqlConfig {
    public static String GetConnectionString() {
        String connectionString = "jdbc:sqlserver://localhost:1433;"
        + "database=Food;"
        + "user=" + System.getenv("MSSQL_USER") + ";"
        + "password=" + System.getenv("MSSQL_PASSWORD")+ ";"
        + "encrypt=false;"
        + "trustServerCertificate=false;"
        + "loginTimeout=30;";

        return connectionString;
    }
}
