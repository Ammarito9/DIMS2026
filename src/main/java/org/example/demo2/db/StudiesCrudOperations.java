package org.example.demo2.db;

import org.example.demo2.dto.Studies;

import java.sql.*;
import java.util.Optional;

public class StudiesCrudOperations {
    static final String DB_URL ="jdbc:postgresql://localhost:5432/DIMS1";
    static final String USER ="postgres";
    static final String PASS="0000";

    public Optional<Studies> getStudiesById(int id){
        Studies studies= null;
        try(Connection connection= DriverManager.getConnection(DB_URL,USER,PASS)){
            Statement statement = connection.createStatement();
            String query="SELECT * FROM studies WHERE id = "+id;
            ResultSet resultSet=statement.executeQuery(query);
            while(resultSet.next()){
                studies = new Studies();
                studies.setId(resultSet.getInt("id"));
                studies.setDescription(resultSet.getString("description"));
                studies.setTitle(resultSet.getString("title"));
            }

        }catch (SQLException throwables){
            throwables.printStackTrace();
        }
        if (studies!=null)
            return Optional.of(studies);
        else
            return Optional.empty();
    }
}
