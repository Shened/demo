package com.example.demo;

import com.example.demo.entities.Color3;
import com.example.demo.entities.Object3D;
import com.example.demo.entities.Transformation;
import com.example.demo.entities.Vector3;
import com.example.demo.objects.*;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class parser {
    private List<Object> allObjectsList;
    private final List<Transformation> transformationList = new ArrayList<>();
    private final List<Material> materialList = new ArrayList<>();
    private final List<Light> lightList = new ArrayList<>();
    private final List<Object3D> object3DList = new ArrayList<>();

    private Image image;
    private Camera camera;

    public void processFile(File file){
        if(file.exists()){
            System.out.println("File loaded with success! Name: " + file.getName());
            this.parseFile(file);
        }
        else{
            System.out.println("Error in loading file ...");
        }
    }

    public boolean parseFile(File chosenFile) {
        allObjectsList = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(chosenFile))) {
            String line;
            String currentObjectType = null;
            List<String> currentObjectLines = new ArrayList<>();
            String prevLine = br.readLine();

            while ((line = br.readLine()) != null) {
                if (line.contains("{")) {
                    currentObjectType = prevLine.trim();
                    currentObjectLines.clear();
                } else if (line.contains("}")) {
                    if (currentObjectType != null) {
                        Object object = parseObject(currentObjectType, currentObjectLines);
                        if (object != null) {
                            allObjectsList.add(object);
                        }
                        currentObjectType = null;
                    }
                } else {
                    currentObjectLines.add(line);
                }
                prevLine = line;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return true;
    }

    private Object parseObject(String objectType, List<String> lines) {
        if (objectType.equals("Image")) {
            String[] res = lines.get(0).trim().split(" ");
            String[] color = lines.get(1).trim().split(" ");
            int resH = Integer.parseInt(res[0]);
            int resV = Integer.parseInt(res[1]);
            float R = Float.parseFloat(color[0]);
            float G = Float.parseFloat(color[1]);
            float B = Float.parseFloat(color[2]);

            Color3 backgroundColor = new Color3(R, G, B);

            image = new Image(resH, resV, backgroundColor);
            return image;
        }
        if (objectType.equals("Transformation")) {
            Transformation transformation = new Transformation(transformationList.size());

            if (lines.size() > 0) {
                for (String line : lines) {
                    String[] variable = line.trim().split(" ");
                    switch (variable[0]) {
                        case "T":
                            transformation.translate(Double.parseDouble(variable[1]), Double.parseDouble(variable[2]), Double.parseDouble(variable[3]));
                            break;
                        case "Rx":
                            transformation.rotateX(Double.parseDouble(variable[1]));
                            break;
                        case "Ry":
                            transformation.rotateY(Double.parseDouble(variable[1]));
                            break;
                        case "Rz":
                            transformation.rotateZ(Double.parseDouble(variable[1]));
                            break;
                        case "S":
                            transformation.scale(Double.parseDouble(variable[1]), Double.parseDouble(variable[2]), Double.parseDouble(variable[3]));
                            break;
                    }
                }
            }

            transformationList.add(transformation);
            return transformation;
        }
        if (objectType.equals("Material")) {
            String[] RGB = lines.get(0).trim().split(" ");
            String[] properties = lines.get(1).trim().split(" ");

            double R = Double.parseDouble(RGB[0]);
            double G = Double.parseDouble(RGB[1]);
            double B = Double.parseDouble(RGB[2]);
            double ambient = Double.parseDouble(properties[0]);
            double diffuse = Double.parseDouble(properties[1]);
            double specular = Double.parseDouble(properties[2]);
            double refraction = Double.parseDouble(properties[3]);
            double refractionIndex = Double.parseDouble(properties[4]);

            Material material = new Material(materialList.size(), R, G, B, ambient, diffuse, specular, refraction, refractionIndex);
            materialList.add(material);
            return material;
        }
        if (objectType.equals("Camera")) {
            int transformationIndex = Integer.parseInt(lines.get(0).trim());
            double fovX = Double.parseDouble(lines.get(1).trim());
            double fovY = Double.parseDouble(lines.get(2).trim());

            camera = new Camera(transformationIndex, fovX, fovY);
            return camera;
        }
        if (objectType.equals("Light")) {
            String[] RGB = lines.get(1).trim().split(" ");
            int transformationIndex = Integer.parseInt(lines.get(0).trim());
            double R = Double.parseDouble(RGB[0]);
            double G = Double.parseDouble(RGB[1]);
            double B = Double.parseDouble(RGB[2]);

            Light light = new Light(lightList.size(), transformationIndex, R, G, B);
            lightList.add(light);
            return light;
        }

        if (objectType.equals("Sphere")) {
            int transformationIndex = Integer.parseInt(lines.get(0).trim());
            int materialIndex = Integer.parseInt(lines.get(1).trim());

            Sphere sphere = new Sphere(transformationIndex, materialIndex);
            object3DList.add(sphere);
            return sphere;
        }
        if (objectType.equals("Box")) {
            int transformationIndex = Integer.parseInt(lines.get(0).trim());
            int materialIndex = Integer.parseInt(lines.get(1).trim());

            Box box = new Box(transformationIndex, materialIndex);
            object3DList.add(box);
            return box;
        }
        if (objectType.equals("Triangles")) {
            int transformationIndex = Integer.parseInt(lines.get(0).trim());

            Triangles triangles = new Triangles(transformationIndex);

            for (int i = 1; i <= lines.size()-1; i++) {
                int materialIndex = Integer.parseInt(lines.get(i).trim());

                List<Vector3> pointsList = new ArrayList<>();

                int endCondicao = i+3;
                while (i < endCondicao) {
                    i++;
                    String[] vertexes = lines.get(i).trim().split(" ");
                    pointsList.add(new Vector3(Double.parseDouble(vertexes[0]), Double.parseDouble(vertexes[1]), Double.parseDouble(vertexes[2])));
                }

                triangles.addTriangle(materialIndex, pointsList.get(0), pointsList.get(1), pointsList.get(2));
            }

            object3DList.add(triangles);
            return triangles;
        }
        return null;
    }

    public List<Object> getAllObjectsList() {
        return allObjectsList;
    }

    public Camera getCamera() {
        return camera;
    }

    public Image getImage() {
        return image;
    }

    public List<Light> getLightList() {
        return lightList;
    }

    public List<Material> getMaterialList() {
        return materialList;
    }

    public List<Object3D> getObject3DList() {
        return object3DList;
    }

    public List<Transformation> getTransformationList() {
        return transformationList;
    }

    public void setTransformations() {
        Transformation cameraTransformation = transformationList.get(camera.getTransformationIndex());
        for (Light light : lightList) {
            Transformation lightTransformation = transformationList.get(light.getTransformationIndex());

            Transformation lightFinalTransformation = new Transformation(-1, cameraTransformation.multiplyMatrix(lightTransformation.getTransformMatrix()));

            light.applyTransformation(lightFinalTransformation);
        }

        for (Object3D object3D : object3DList) {

            Transformation objectTransformation = transformationList.get(object3D.getObjectTransformationId());
            Transformation objectFinalTransformation = new Transformation(-1, cameraTransformation.multiplyMatrix(objectTransformation.getTransformMatrix()));

            object3D.setTransformation(objectFinalTransformation);

            Transformation objectFinalTransformationInverted = new Transformation(-1, cameraTransformation.multiplyMatrix(objectTransformation.getTransformMatrix()));
            objectFinalTransformationInverted.invertMatrix();

            object3D.setInverseTransformation(objectFinalTransformationInverted);

            Transformation objectFinalTransformationInvertedTransposed = new Transformation(-1, cameraTransformation.multiplyMatrix(objectTransformation.getTransformMatrix()));
            objectFinalTransformationInvertedTransposed.invertMatrix();
            objectFinalTransformationInvertedTransposed.transposeMatrix();

            object3D.setTransposeInverseTransformation(objectFinalTransformationInvertedTransposed);
        }

        for (Material material : materialList) {
            material.setAmbientColor();
            material.setDiffuseColor();
        }
    }
}