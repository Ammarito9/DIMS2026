package org.example.demo2.Controller;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class StudiesController {
    @FXML
    private Label welcomeText;

    @FXML
    protected void onHelloButtonClick() {
        welcomeText.setText("Welcome to JavaFX Application!");
    }
}
