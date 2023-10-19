package com.example.demo;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class parser {
    private List<Object> allObjectsList;
    /*private final List<Transformation> transformationList = new ArrayList<>();
    private final List<Material> materialList = new ArrayList<>();
    private final List<Light> lightList = new ArrayList<>();
    private final List<Object3D> object3DList = new ArrayList<>();
    private Image image;
    private Camera camera;*/

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
        return null;
    }
}