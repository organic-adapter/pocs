package com.example.demo.resourceAccessLayerExample;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.example.demo.model.Bread;
import com.example.demo.resourceAccessLayerExample.exceptions.CannotFindKey;

public class BreadPostgreSqlAccess implements BreadAccess {
    private final String connectionString;

    public BreadPostgreSqlAccess(String connectionString) {
        this.connectionString = connectionString;
    }

    private List<Bread> map(ResultSet resultSet) throws SQLException {
        List<Bread> returnMe = new ArrayList<Bread>();

        while (resultSet.next()) {
            Bread bread = new Bread(
                    resultSet.getString("id"),
                    resultSet.getString("type"),
                    resultSet.getString("displayName"),
                    resultSet.getFloat("quantity"));
            returnMe.add(bread);
        }

        return returnMe;
    }

    @Override
    public Bread get(String id) throws CannotFindKey {
        String query = "SELECT * FROM public.bread_get(?, NULL);";

        try (Connection connection = DriverManager.getConnection(connectionString);
                PreparedStatement statement = connection.prepareStatement(query);) {
            statement.setString(1, id);
            ResultSet resultSet = statement.executeQuery();
            List<Bread> mapped = map(resultSet);
            if (mapped.size() == 0)
                throw new CannotFindKey();
            return mapped.get(0);

        } catch (SQLException e) {
            throw new CannotFindKey();
        }
    }

    @Override
    public Bread save(Bread bread) {
        // noop -- build out once we have a working get for both systems.
        return bread;
    }

    @Override
    public void delete(String id) {
        // noop -- build out once we have a working get for both systems.
    }
}
