package org.example.demo2.Controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Spinner;
import javafx.scene.control.TextField;

public class SupervisesController {

    @FXML
    private Button btn_Clear;

    @FXML
    private Button btn_Close;

    @FXML
    private Button btn_Delete;

    @FXML
    private Button btn_Get;

    @FXML
    private Button btn_Save;

    @FXML
    private Button btn_Update;

    @FXML
    private Label lbl_Performance;

    @FXML
    private Label lbl_Student;

    @FXML
    private Spinner<Integer> spinner_Performance;

    @FXML
    private TextField tf_Student;

    @FXML
    void Clear_Button_On_Action(ActionEvent event) {
        tf_Student.clear();
        spinner_Performance.cancelEdit();
    }

    @FXML
    void Close_Button_On_Action(ActionEvent event) {

    }

    @FXML
    void Delete_Button_On_Action(ActionEvent event) {

    }

    @FXML
    void Get_Button_On_Action(ActionEvent event) {

    }

    @FXML
    void Save_Button_On_Action(ActionEvent event) {

    }

    @FXML
    void Update_Button_On_Action(ActionEvent event) {

    }

}
