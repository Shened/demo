package com.example.demo;

import com.example.demo.objects.Box;
import com.example.demo.objects.Sphere;
import com.example.demo.objects.Triangles;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("hello-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 800, 600);
        stage.setTitle("Ray Caster");
        stage.setScene(scene);
        stage.show();


        //CRIAR TRIANGULOS
        Triangles segment = new Triangles(0);

        segment.addTriangle(3, -30, -6, -6, -18, -6, -6, -24, 0, 6);
        segment.addTriangle(3, -18, -6, -6, -18, 6, -6, -24, 0, 6);

        // Exemplo de uso dos getters
        System.out.println("Transformation: " + segment.getTransformation());
        System.out.println("First Triangle's Material: " + segment.getTriangles().get(0).getMaterial());

        //CRIAR ESFERAS
        Sphere sphere = new Sphere(0, 3);

        // Exemplo de uso dos getters
        System.out.println("Transformation: " + sphere.getTransformation());
        System.out.println("Material: " + sphere.getMaterial());

        //CRIAR BOX
        Box box = new Box(0, 3);

        // Exemplo de uso dos getters
        System.out.println("Transformation: " + box.getTransformation());
        System.out.println("Material: " + box.getMaterial());
    }


    public static void main(String[] args) {
        launch();
    }
}