package org.example.demo2.Controller;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import org.example.demo2.db.StudiesCrudOperations;
import org.example.demo2.dto.Studies;

import java.util.Optional;

public class StudiesController {

    @FXML
    private Button btn_clear;

    @FXML
    private Button btn_close;

    @FXML
    private Button btn_delete;

    @FXML
    private Button btn_get;

    @FXML
    private Button btn_save;

    @FXML
    private Button btn_update;

    @FXML
    private TextField tf_description;

    @FXML
    private TextField tf_id;

    @FXML
    private TextField tf_title;

    @FXML
    void Clear_Btn_On_Action(ActionEvent event) {
        tf_id.clear();
        tf_description.clear();
        tf_title.clear();

    }

    @FXML
    void Close_Btn_On_Action(ActionEvent event) {
        Platform.exit();
    }

    @FXML
    void Delete_Btn_On_Action(ActionEvent event) {
        checkId(tf_id.getText(), event);
        StudiesCrudOperations crudOperations=new StudiesCrudOperations();
        int id = Integer.parseInt(tf_id.getText());
//continue the delete part and make a function in the studies crud operations file and then make the rest of the functions
    }

    @FXML
    void Get_Btn_On_Action(ActionEvent event) {
        checkId(tf_id.getText(), event);
        StudiesCrudOperations crudOperations=new StudiesCrudOperations();
        int id = Integer.parseInt(tf_id.getText());
        Optional<Studies> studies=crudOperations.getStudiesById(id);
        if(studies.isPresent()){
            tf_id.setText(Integer.toString(studies.get().getId()));
            tf_description.setText(studies.get().getDescription());
            tf_title.setText(studies.get().getTitle());
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Car with id " + id + " not found");
            alert.showAndWait();
        }

    }

    @FXML
    void Save_Btn_On_Action(ActionEvent event) {

    }

    @FXML
    void Update_Btn_On_Action(ActionEvent event) {

    }
    public void checkId(String id, ActionEvent event) {
        if (id.isEmpty() || Integer.parseInt(id) <= 0) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Id is wrong!");
            alert.showAndWait();
            Clear_Btn_On_Action(event);
        }
    }

}
