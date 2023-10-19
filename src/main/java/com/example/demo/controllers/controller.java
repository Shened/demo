package com.example.demo.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import com.example.demo.parser;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import org.controlsfx.control.tableview2.filter.parser.Parser;

import java.io.File;

public class controller {

    @FXML
    private Stage stage;

    @FXML
    protected void start() {

    }

    public void loadFile(ActionEvent actionEvent) {
        FileChooser fileChooser = new FileChooser();

        fileChooser.setTitle("Escolher o ficheiro para o cenário");

        FileChooser.ExtensionFilter extensionFilter = new FileChooser.ExtensionFilter("Arquivo de texto (*.txt)", "*.txt");

        fileChooser.getExtensionFilters().add(extensionFilter);

        File selectedFile = fileChooser.showOpenDialog(stage);

        if(selectedFile != null){
            parser parser = new parser();
            parser.processFile(selectedFile);
        }
    }
}