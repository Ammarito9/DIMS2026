package org.example.demo2.db;

import org.example.demo2.dto.Studies;
import org.example.demo2.dto.Supervises;

import java.sql.*;
import java.util.Optional;

public class SupervisesCrudOperations {
//    static final String DB_URL ="jdbc:postgresql://localhost:5432/DIMS1";
    static final String DB_URL ="jdbc:postgresql://localhost:5432/SchoolManagementSystem3";
    static final String USER ="postgres";
    // static final String PASS="0000";
    static final String PASS="0115210D";

    public Optional<Supervises> GetSupervisesById(int Id) {
        Supervises supervises = null;
        try(Connection connection = DriverManager.getConnection(DB_URL, USER, PASS)) {
            Statement statement = connection.createStatement();
            String query = "SELECT * FROM supervises WHERE supervises_id =" + Id;
            ResultSet resultSet = statement.executeQuery(query);
            while(resultSet.next())
            {
                supervises = new Supervises();

                supervises.SetSupervisesId(resultSet.getInt("supervises_id"));
                supervises.SetAdvisorId(resultSet.getInt("adviser_id"));
                supervises.SetStudyId(resultSet.getInt("study_id"));
                supervises.SetStudent(resultSet.getString("student"));
                supervises.SetPerformance(resultSet.getInt("performance"));
            }
        }
        catch(SQLException e) {
            e.printStackTrace();
        }

        if(supervises == null)
            return Optional.empty();
        else
            return Optional.of(supervises);
    }

    public String GetStudyTitleRelatedToSupervisesId(int Id) {
        String StudyTitle = "";
        try(Connection connection = DriverManager.getConnection(DB_URL, USER, PASS)) {
            Statement statement = connection.createStatement();
            String query = "SELECT st.title FROM public.supervises su " +
                    "JOIN public.studies st " +
                    "ON su.study_id = st.id " +
                    "WHERE su.supervises_id = " + Id + ";";

            ResultSet resultSet = statement.executeQuery(query);

            if(resultSet.next())
                StudyTitle = resultSet.getString("title");
        }
        catch (SQLException e) {
            e.printStackTrace();
        }

        return StudyTitle;
    }

    public String GetAdviserNameRelatedToSupervisesId(int Id) {
        String AdviserName = "";
        try(Connection connection = DriverManager.getConnection(DB_URL, USER, PASS)) {
            Statement statement = connection.createStatement();
            String query = "SELECT ad.name FROM public.supervises su " +
                    "JOIN public.advisers ad " +
                    "ON su.adviser_id = ad.id " +
                    "WHERE su.supervises_id = " + Id + ";";

            ResultSet resultSet = statement.executeQuery(query);

            if(resultSet.next())
                AdviserName = resultSet.getString(1);
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        return AdviserName;
    }
    /*
    [] Complete the other operations ([+] Insert,[+] Update,[+] Delete,[+] CheckId)
    [] After that go implement all the event functions.
    [] Test it
    */


    public int InsertSupervises(Supervises supervises) {
        if(GetSupervisesById(supervises.GetSupervisesId()).isPresent()) return -1;

        int result = 0;
        try (Connection connection = DriverManager.getConnection(DB_URL, USER, PASS)) {
            Statement statement = connection.createStatement();
            String record = supervises.GetSupervisesId() + "," + supervises.GetAdvisorId() + "," + supervises.GetStudyId() + ",\'" + supervises.GetStudent() + "\'," + supervises.GetPerformance();
            String query = "INSERT INTO supervises (supervises_id, adviser_id, study_id, student, performance) VALUES (" + record + ");";

            result = statement.executeUpdate(query);
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    public int DeleteSupervises(int Id) {
        if (!GetSupervisesById(Id).isPresent()) return -1;

        int result = 0;
        try(Connection connection = DriverManager.getConnection(DB_URL, USER, PASS)) {
            Statement statement = connection.createStatement();
            String query = "DELETE FROM supervises WHERE supervises_id = " + Id + ";";
            result = statement.executeUpdate(query);
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
        return result;
    }

    public int UpdateSupervises(Supervises supervises) {
        if (!GetSupervisesById(supervises.GetSupervisesId()).isPresent()) return -1;

        int result = 0;
        try(Connection connection = DriverManager.getConnection(DB_URL, USER, PASS)) {
            Statement statement = connection.createStatement();
            String query = "UPDATE public.supervises " +
                    "SET " +
                    "adviser_id = " + supervises.GetAdvisorId() + ", " +
                    "study_id = " + supervises.GetStudyId() + ", " +
                    "student = \'" + supervises.GetStudent() + "\', " +
                    "performance = " + supervises.GetPerformance() + " " +
                    "WHERE supervises.supervises_id = " + supervises.GetSupervisesId() + ";";

            result = statement.executeUpdate(query);
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }
}
