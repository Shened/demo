package com.example.demo.objects;

import com.example.demo.entities.Hit;
import com.example.demo.entities.Object3D;
import com.example.demo.entities.Ray;

public class Box implements Object3D {

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

    @Override
    public boolean intersect(Ray ray, Hit hit) {
        return false;
    }
}