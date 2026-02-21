package org.example.demo2;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class Pro1Application extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Pro1Application.class.getResource("MainView.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 450, 330);
        stage.setTitle("Project1");
        stage.setScene(scene);
        stage.show();
    }
}
