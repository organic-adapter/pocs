package com.example.demo.configuration;

public class PostgreSqlConfig {
    public static String GetConnectionString() {
        String connectionString = "jdbc:postgresql://localhost:5432/food?"
        + "user=" + System.getenv("POSTGRES_USER") + "&"
        + "password=" + System.getenv("POSTGRES_PASSWORD")+ "&"
        + "ssl=false";

        return connectionString;
    }
}
