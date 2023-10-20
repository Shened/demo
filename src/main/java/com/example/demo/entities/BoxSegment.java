package com.example.demo.entities;

public class BoxSegment {

    // Campos para transformação e material
    private int transformation;
    private int material;

    public BoxSegment(int transformation, int material) {
        this.transformation = transformation;
        this.material = material;
    }

    // Getters e Setters para BoxSegment
    public int getTransformation() {
        return transformation;
    }

    public void setTransformation(int transformation) {
        this.transformation = transformation;
    }

    public int getMaterial() {
        return material;
    }

    public void setMaterial(int material) {
        this.material = material;
    }
}