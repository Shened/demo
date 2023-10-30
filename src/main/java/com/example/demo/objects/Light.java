package com.example.demo.objects;

import com.example.demo.entities.Color3;
import com.example.demo.entities.Transformation;
import com.example.demo.entities.Vector3;
import com.example.demo.entities.Vector4;

public class Light {
    private int transformationIndex;
    private Color3 lightColor;

    private Vector3 lightPosition;

    public Light(int transformationIndex, Color3 lightColor,Vector3 lightPosition) {
        this.transformationIndex = transformationIndex;
        this.lightColor = lightColor;
        this.lightPosition=lightPosition;
    }
    public Light(int size, int transformationIndex, double r, double g, double b){
        this.transformationIndex = 0;
        this.lightColor = new Color3(0,0,0);
        this.lightPosition = new Vector3(0.0,0.0,0.0);
    }

    public int getTransformationIndex() {
        return transformationIndex;
    }

    public void setTransformationIndex(int transformationIndex) {
        this.transformationIndex = transformationIndex;
    }

    public Color3 getLightColor() {
        return lightColor;
    }

    public void setLightColor(Color3 lightColor) {
        this.lightColor = lightColor;
    }

    public Vector3 getLightPosition(){
        return this.lightPosition;
    }

    public void setLightPosition(Vector3 lightPosition){
        this.lightPosition=lightPosition;
    }

    public void applyTransformation(Transformation transformation){
        Vector4 vector4 = new Vector4(0,0,0,1);
        this.lightPosition = transformation.applyTransformation(vector4);
    }
}

