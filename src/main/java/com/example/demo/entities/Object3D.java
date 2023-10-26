package com.example.demo.entities;

public interface Object3D {
    public abstract boolean intersect(Ray ray, Hit hit);
}
