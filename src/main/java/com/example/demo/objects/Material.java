package com.example.demo.objects;

import com.example.demo.entities.Color3;

public class Material {
    private Color3 color;
    private double ambient;
    private double diffuse;
    private double specular;
    private double refraction;
    private double refractionIndex;

    public Material(Color3 color, double ambient, double diffuse, double specular, double refraction, double refractionIndex) {
        this.color = color;
        this.ambient = ambient;
        this.diffuse = diffuse;
        this.specular = specular;
        this.refraction = refraction;
        this.refractionIndex = refractionIndex;
    }
    public Material(int size, double r, double g, double b, double ambient, double diffuse, double specular, double refraction, double refractionIndex){
        this.color = new Color3(0,0,0);
        this.ambient = 0;
        this.diffuse = 0;
        this.specular = 0;
        this.refraction = 0;
        this.refractionIndex = 0;
    }

    public Color3 getColor() {
        return color;
    }

    public void setColor(Color3 color) {
        this.color = color;
    }

    public double getAmbient() {
        return ambient;
    }

    public void setAmbient(double ambient) {
        this.ambient = ambient;
    }

    public double getDiffuse() {
        return diffuse;
    }

    public void setDiffuse(double diffuse) {
        this.diffuse = diffuse;
    }

    public double getSpecular() {
        return specular;
    }

    public void setSpecular(double specular) {
        this.specular = specular;
    }

    public double getRefraction() {
        return refraction;
    }

    public void setRefraction(double refraction) {
        this.refraction = refraction;
    }

    public double getRefractionIndex() {
        return refractionIndex;
    }

    public void setRefractionIndex(double refractionIndex) {
        this.refractionIndex = refractionIndex;
    }
}
