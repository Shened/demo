package com.example.demo.objects;

public class Box {

    // Campos para transformação e material
    private int transformation;
    private int material;

    public Box(int transformation, int material) {
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