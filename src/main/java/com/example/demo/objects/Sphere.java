package com.example.demo.objects;

import com.example.demo.entities.*;

public class Sphere implements Object3D {

    // Campos para transformação e material
    private int transformation;
    private int material;

    public Sphere(int transformation, int material) {
        this.transformation = transformation;
        this.material = material;
    }

    // Getters e Setters para SphereSegment
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