package com.example.demo.controllers;

import com.example.demo.entities.Color3;
import com.example.demo.entities.Ray;
import com.example.demo.entities.Vector3;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.ChoiceBox;
import javafx.scene.paint.Color;
import javafx.stage.FileChooser;
import com.example.demo.parser;
import java.io.File;
import org.apache.commons.math3.util.FastMath;


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

    public void startProgram(ActionEvent event){
        System.out.println("Start button clicked!");
        primaryRaysCalculations();
    }

    public void primaryRaysCalculations() {
        double Hres = parser.getImage().getHorRes();
        double Vres = parser.getImage().getVerRes();
        double distance = parser.getCamera().getDistance();
        double radianFoV = FastMath.toRadians(parser.getCamera().getFov());
        double height = 2.0 * distance * Math.tan(radianFoV / 2.0);
        double width = height * Hres / Vres;
        double s = height / Vres;
        rayTrajectoryGeneration(Hres, Vres, width, height, s, distance);
    }

    private void rayTrajectoryGeneration(double Hres, double Vres, double width, double height, double s, double distance) {
        int i;
        int j;
        Pixel[][] pixel = new Pixel[(int) Hres][(int) Vres];
        double x, y, z;
        Vector3 origin = new Vector3(0.0, 0.0, distance);
        for (j = 0; j < Vres; j++) {
            for (i = 0; i < Hres; i++) {
                x = (i + 0.5) * s - width / 2.0;
                y = -(j + 0.5) * s + height / 2.0;
                z = 0.0;
                Vector3 direction = new Vector3(x - 0.0, y - 0.0, z - distance);
                Vector3 directionNormalized = direction.normalizeVector();

                //TESTE DOS VETORES DIRECAO
                if(i<10&&j<10){
                    System.out.println("Vetor linha "+j+" coluna "+i+":\nX:"+directionNormalized.getX()+"\nY:"+directionNormalized.getY()+"\nZ:"+directionNormalized.getZ());
                }

                Ray ray = new Ray(origin, directionNormalized);
                Color3 color = traceRay(ray, rec.get());
                color.checkRange();
                pixel[i][j] = new Pixel();
                double v = 255.0 * color.getRed();
                pixel[i][j].r = (int) v;
                double v1 = 255.0 * color.getGreen();
                pixel[i][j].g = (int) v1;
                double v2 = 255.0 * color.getBlue();
                pixel[i][j].b = (int) v2;

                double maxPixel = Vres*Hres;
                double incrementation = 100/maxPixel;
                if(u-3000 == previousN){
                    //progressBarIncrementation(((u-previousN)*incrementation)/100);
                    previousN = u;
                }
            }
        }
        GraphicsContext gc = canvas.getGraphicsContext2D();
        for (j = 0; j < Vres; j++) {
            for (i = 0; i < Hres; i++) {
                gc.setFill(Color.rgb(pixel[i][j].r, pixel[i][j].g, pixel[i][j].b));
                gc.fillRect(i, j, 1, 1);
            }
        }
    }
}
