package org.example.demo2.Controller;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import org.example.demo2.db.*;
import org.example.demo2.dto.Supervises;

import java.util.Optional;

import static java.lang.Integer.parseInt;

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
    private Label lbl_Advisor_ID;

    @FXML
    private Label lbl_Advisor_Name;

    @FXML
    private Label lbl_Advisor_Name_static;

    @FXML
    private Label lbl_Id;

    @FXML
    private Label lbl_Performance;

    @FXML
    private Label lbl_Student;

    @FXML
    private Label lbl_Study_ID;

    @FXML
    private Label lbl_Study_title;

    @FXML
    private Label lbl_Study_title_static;

    @FXML
    private TextField tf_Performance;

    @FXML
    private TextField tf_Advisor_ID;

    @FXML
    private TextField tf_Id;

    @FXML
    private TextField tf_Student;

    @FXML
    private TextField tf_Study_ID;

    SupervisesCrudOperations ops = new SupervisesCrudOperations();

    @FXML
    void Clear_Button_On_Action(ActionEvent event) {
        tf_Student.clear();
        tf_Id.clear();
        tf_Advisor_ID.clear();
        tf_Study_ID.clear();
        tf_Performance.clear();
        lbl_Study_title.setText("-");
        lbl_Advisor_Name.setText("-");
    }

    @FXML
    void Close_Button_On_Action(ActionEvent event) {
        Platform.exit();
    }

    @FXML
    void Delete_Button_On_Action(ActionEvent event) {
        int result = 0;

        Alert ConfirmationAlert = new Alert(Alert.AlertType.CONFIRMATION);
        ConfirmationAlert.setTitle("Confirm Deletion!");
        ConfirmationAlert.setHeaderText("Are you sure?");
        ConfirmationAlert.setContentText("Do you want to continue?");
        Optional<ButtonType> ConfirmationResult = ConfirmationAlert.showAndWait();

        if(ConfirmationResult.isPresent() && ConfirmationResult.get() == ButtonType.OK) {
            result = ops.DeleteSupervises(parseInt(tf_Id.getText()));

            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle(null);
            alert.setContentText(null);

            if(result > 0) {
                alert.setHeaderText("Deletion done successfully!");
                alert.show();
            }
            else {
                alert.setHeaderText("Something went wrong! Deletion is NOT done!");
                alert.show();
            }
        }
    }

    @FXML
    void Get_Button_On_Action(ActionEvent event) {
        Optional<Supervises> supervises = ops.GetSupervisesById(parseInt(tf_Id.getText()));
        if(supervises.isPresent()) {
            tf_Id.setText(String.valueOf(supervises.get().GetSupervisesId()));
            tf_Study_ID.setText(String.valueOf(supervises.get().GetStudyId()));
            tf_Advisor_ID.setText(String.valueOf(supervises.get().GetAdvisorId()));
            tf_Student.setText(supervises.get().GetStudent());
            tf_Performance.setText(String.valueOf(supervises.get().GetPerformance()));

            lbl_Advisor_Name.setText(ops.GetAdviserNameRelatedToSupervisesId(supervises.get().GetSupervisesId()));
            lbl_Study_title.setText(ops.GetStudyTitleRelatedToSupervisesId(supervises.get().GetSupervisesId()));
        }
        else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setContentText("User is not found!");
        }
    }

    @FXML
    void Save_Button_On_Action(ActionEvent event) {
        Optional<Supervises> supervises = GetSupervisesRecord(tf_Id.getText(), tf_Advisor_ID.getText(), tf_Study_ID.getText(), tf_Student.getText(), tf_Performance.getText());

        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(null);
        alert.setContentText(null);

        if(!supervises.isPresent()) {
            alert.setHeaderText("Error! The input entered is NOT valid!");
            alert.show();
            return;
        }

        int result = 0;

        result = ops.InsertSupervises(supervises.get());

        if(!(result > 0)) {
            alert.setHeaderText("The insertion failed!");
            alert.show();
            return;
        }

        alert.setHeaderText("The supervises with ID (" + supervises.get().GetSupervisesId() + ") saved successfully!");
        alert.show();
    }

    @FXML
    void Update_Button_On_Action(ActionEvent event) {
        Optional<Supervises> supervises = GetSupervisesRecord(tf_Id.getText(), tf_Advisor_ID.getText(), tf_Study_ID.getText(), tf_Student.getText(), tf_Performance.getText());

        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(null);
        alert.setContentText(null);

        int result = 0;

        if(!supervises.isPresent()) {
            alert.setHeaderText("Error! Please enter numeric for (Ids, Performance) and text for (Student)");
            alert.show();
            return;
        }

        result = ops.UpdateSupervises(supervises.get());

        if(!(result > 0)) {
            alert.setHeaderText("Error! The update failed");
            alert.show();
            return;
        }

        alert.setHeaderText("The supervises with ID (" + supervises.get().GetSupervisesId() + ") updated successfully!");
        alert.show();
    }

    private Optional<Supervises> GetSupervisesRecord(String SupervisesId,String AdviserId,String StudyId,String Student,String Performance) {
        if(isNumeric(SupervisesId) && isNumeric(AdviserId) && isNumeric(StudyId) && isNumeric(Performance)) {
            return Optional.of(new Supervises(parseInt(SupervisesId), parseInt(AdviserId), parseInt(StudyId), Student, parseInt(Performance)));
        }
        else {
            return Optional.empty();
        }
    }

    public static boolean isNumeric(String str) {
        return str != null && str.matches("\\d+");
    }
}
