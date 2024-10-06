package com.dictiofx.code;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.FileNotFoundException;
import java.io.IOException;

public class HelloApplication extends Application {

    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("hello-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load());

        HelloController controller = fxmlLoader.getController();

        stage.setTitle("Hello!");
        stage.setScene(scene);
        stage.setResizable(false);

        stage.show();

    }

    public static void main(String[] args) throws FileNotFoundException {
        launch();

    }
}