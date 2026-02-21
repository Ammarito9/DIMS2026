package org.example.demo2.db;

import org.example.demo2.dto.Adviser;

import java.sql.*;
import java.util.Optional;

public class AdviserCrudOperations {
    static final String DB_URL = "jdbc:postgresql://localhost:5432/DIMS1";
    static final String USER = "postgres";
    static final String PASS="0000";

    public Optional<Adviser> getAdviserById(int id) {
        Adviser adviser = null;
        try (Connection connection = DriverManager.getConnection(DB_URL, USER, PASS)){
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM adviser WHERE id = " + id);
            while (resultSet.next()) {
                adviser = new Adviser();
                adviser.setId(resultSet.getInt("id"));
                adviser.setName(resultSet.getString("name"));
                adviser.setDepartment(resultSet.getString("department"));
            }

        }catch(SQLException throwables){
            throwables.printStackTrace();}
        if(adviser != null){
            return Optional.of(adviser);
        }
        else {
            return Optional.empty();
        }
    }

    public int deleteAdviserById(int id) {
        int result =0;
        try (Connection connection = DriverManager.getConnection(DB_URL, USER, PASS)){
            Statement statement = connection.createStatement();
            result =statement.executeUpdate("DELETE FROM adviser WHERE id = " + id);
        }catch (Exception e){
            throw new RuntimeException();}
        return result;
    }

    public int updateAdviserById(Adviser adviser) {
        int result = 0;
        try (Connection connection = DriverManager.getConnection(DB_URL, USER, PASS)) {
            Statement statement = connection.createStatement();
            if(getAdviserById(adviser.getId()).isPresent()) {
                String query = "UPDATE adviser SET " +
                        "name =  \'" + adviser.getName() +"\', " +
                        "department = \'" + adviser.getDepartment() + "\' WHERE id = " + adviser.getId() + ";";
                result = statement.executeUpdate(query);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return result;
    }

    public int insertAdviserById(Adviser adviser) {
        int result = 0;
        try (Connection connection = DriverManager.getConnection(DB_URL, USER, PASS)) {
            Statement statement = connection.createStatement();
            String params = adviser.getId() + ", \'" + adviser.getName() + "\',\'" + adviser.getDepartment() + "\'";
            // Check if there exist a record on that id
            if(getAdviserById(adviser.getId()).isPresent()) {
                result = -1;
            } else {
                String query = "INSERT INTO adviser (id, name, department) VALUES (" + params + ");";
                result = statement.executeUpdate(query);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return result;
    }
}
