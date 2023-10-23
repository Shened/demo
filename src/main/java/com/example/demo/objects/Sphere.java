package com.example.demo.objects;

import com.example.demo.entities.Hit;
import com.example.demo.entities.Ray;
import com.example.demo.entities.Vector3;
import com.example.demo.entities.Vector4;

public class Sphere {

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
}