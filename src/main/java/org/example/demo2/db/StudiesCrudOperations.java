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
    public int insertStudiesById(Studies studies) {
        int result = 0;
        try (Connection connection = DriverManager.getConnection(DB_URL, USER, PASS)) {
            Statement statement = connection.createStatement();
            String params = studies.getId() + ", \'" + studies.getDescription() + "\',\'" + studies.getTitle() + "\'";
            // Check if there exist a record on that id
            if(getStudiesById(studies.getId()).isPresent()) {
                result = -1;
            } else {
                String query = "INSERT INTO studies (id, description, title) VALUES (" + params + ");";
                result = statement.executeUpdate(query);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return result;
    }
    public int deleteStudiesById(int id ){
        int result =0;
        try(Connection connection= DriverManager.getConnection(DB_URL, USER, PASS)){
            Statement statement=connection.createStatement();
            String query =" DELETE FROM studies WHERE id = " + id;
            result = statement.executeUpdate(query);
        }catch(Exception e){
            throw new RuntimeException(e);
        }
        return result;
    }
    public int updateStudiesById(Studies studies) {
        int result = 0;
        try (Connection connection = DriverManager.getConnection(DB_URL, USER, PASS)) {
            Statement statement = connection.createStatement();
            String params = studies.getId() + ", \'" + studies.getDescription() + "\',\'" + studies.getTitle()+ "\'";
            // Check if there exist a record on that id
            if(getStudiesById(studies.getId()).isPresent()) {
                String query = "UPDATE studies SET " +
                        "description =  \'" + studies.getDescription() +"\', " +
                        "title = \'" + studies.getTitle()  +  "\' WHERE id = " + studies.getId() + ";";
                //System.out.printf("Query: " + query);
                result = statement.executeUpdate(query);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return result;
    }

}
