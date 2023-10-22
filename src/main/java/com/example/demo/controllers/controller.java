package com.example.demo.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.stage.FileChooser;
import com.example.demo.parser;
import java.io.File;

public class controller {

    @FXML
    private ChoiceBox<String> selectionBox;

    @FXML
    public void initialize() {
        // Populate the ChoiceBox
        selectionBox.getItems().addAll("1", "2", "3");
        selectionBox.setValue("1"); // Set default value

        // Handle selection changes
        selectionBox.getSelectionModel().selectedItemProperty().addListener((v, oldValue, newValue) -> {
            System.out.println("Selected value: " + newValue);
        });
    }

    @FXML
    protected void start() {
        // Your code here
    }

    public void loadFile(ActionEvent actionEvent) {
        FileChooser fileChooser = new FileChooser();

        fileChooser.setTitle("Escolher o ficheiro para o cenário");

        FileChooser.ExtensionFilter extensionFilter = new FileChooser.ExtensionFilter("Arquivo de texto (*.txt)", "*.txt");
        fileChooser.getExtensionFilters().add(extensionFilter);

        File selectedFile = fileChooser.showOpenDialog(null);

        if(selectedFile != null){
            parser parser = new parser();
            parser.processFile(selectedFile);
        }
    }
}
