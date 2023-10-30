package com.example.demo.entities;

public abstract class Object3D {
    private final int objectTransformationId;
    private Transformation transformation;
    private Transformation inverseTransformation;
    private Transformation transposeInverseTransformation;

    public Object3D(int objectTransformationIndex){ this.objectTransformationId = objectTransformationIndex;}

    public int getObjectTransformationId() {
        return objectTransformationId;
    }

    public Transformation getInverseTransformation() {
        return inverseTransformation;
    }

    public Transformation getTransformation() {
        return transformation;
    }

    public Transformation getTransposeInverseTransformation() {
        return transposeInverseTransformation;
    }

    public void setInverseTransformation(Transformation inverseTransformation) {
        this.inverseTransformation = inverseTransformation;
    }

    public void setTransformation(Transformation transformation) {
        this.transformation = transformation;
    }

    public void setTransposeInverseTransformation(Transformation transposeInverseTransformation) {
        this.transposeInverseTransformation = transposeInverseTransformation;
    }

    public abstract boolean intersect(Ray ray, Hit hit);

}
