package org.example.demo2.Controller;

import org.example.demo2.db.AdviserCrudOperations;
import org.example.demo2.dto.Adviser;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

import java.util.Optional;

public class AdvisersController {

    @FXML
    private TextField adviserId;

    @FXML
    private TextField adviserName;

    @FXML
    private TextField adviserDepartment;

    @FXML
    private Button saveAdviser;

    @FXML
    private Button updateAdviser;

    @FXML
    private Button deleteAdviser;

    @FXML
    private Button close;

    @FXML
    private Button getAdviser;

    @FXML
    private Button clearAdviser;

    @FXML
    void clearAdviser(ActionEvent event) {
        adviserId.setText("");
        adviserName.setText("");
        adviserDepartment.setText("");
    }

    @FXML
    public void close(ActionEvent event) {
        Platform.exit();
    }

    @FXML
    void deleteAdviser(ActionEvent event) {
        checkId(adviserId.getText(), event);
        AdviserCrudOperations crudOperations = new AdviserCrudOperations();
        int id = Integer.parseInt(adviserId.getText());
        int result = crudOperations.deleteAdviserById(id);

        if (result > 0) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Success");
            alert.setHeaderText("Adviser with ID " + id + " deleted successfully.");
            alert.showAndWait();
            clearAdviser(event);
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Could not delete Adviser. Check if the ID exists.");
            alert.showAndWait();
        }
    }

    @FXML
    void getAdviser(ActionEvent event) {
        checkId(adviserId.getText(), event);
        AdviserCrudOperations crudOperations = new AdviserCrudOperations();
        int id = Integer.parseInt(adviserId.getText());

        Optional<Adviser> adviser = crudOperations.getAdviserById(id);

        if (adviser.isPresent()) {
            adviserId.setText(Integer.toString(adviser.get().getId()));
            adviserName.setText(adviser.get().getName());
            adviserDepartment.setText(adviser.get().getDepartment());
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Adviser with ID " + id + " not found.");
            alert.showAndWait();
        }
    }

    @FXML
    void saveAdviser(ActionEvent event) {
        checkId(adviserId.getText(), event);
        Adviser adviser = new Adviser();
        adviser.setId(Integer.parseInt(adviserId.getText()));
        adviser.setName(adviserName.getText());
        adviser.setDepartment(adviserDepartment.getText());

        AdviserCrudOperations crudOperations = new AdviserCrudOperations();
        int res = crudOperations.insertAdviserById(adviser);

        if (res > 0) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Success");
            alert.setHeaderText("Adviser saved successfully.");
            alert.showAndWait();
        } else if (res == -1) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("An Adviser with ID " + adviserId.getText() + " already exists!");
            alert.showAndWait();
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Error saving the Adviser!");
            alert.showAndWait();
        }
    }

    @FXML
    void updateAdviser(ActionEvent event) {
        checkId(adviserId.getText(), event);
        Adviser adviser = new Adviser();
        adviser.setId(Integer.parseInt(adviserId.getText()));
        adviser.setName(adviserName.getText());
        adviser.setDepartment(adviserDepartment.getText());

        AdviserCrudOperations crudOperations = new AdviserCrudOperations();
        int res = crudOperations.updateAdviserById(adviser);

        if (res > 0) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Success");
            alert.setHeaderText("Adviser updated successfully.");
            alert.showAndWait();
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Error updating Adviser. Make sure the ID exists.");
            alert.showAndWait();
        }
    }

    // Utility method to ensure the ID is valid before sending it to the database
    public void checkId(String id, ActionEvent event) {
        if (id == null || id.isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("ID field cannot be empty!");
            alert.showAndWait();
            clearAdviser(event);
            return;
        }
        try {
            int parsedId = Integer.parseInt(id);
            if (parsedId <= 0) {
                throw new NumberFormatException();
            }
        } catch (NumberFormatException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("ID must be a positive number!");
            alert.showAndWait();
            clearAdviser(event);
        }
    }
}