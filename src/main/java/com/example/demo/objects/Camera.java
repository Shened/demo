package com.example.demo.objects;

public class Camera {
    private int transformationIndex;
    private double distance;
    private double fov;

    public Camera(int transformationIndex, double distance, double fov) {
        this.transformationIndex = transformationIndex;
        this.distance = distance;
        this.fov = fov;
    }
    public Camera(){
        this.transformationIndex = 0;
        this.distance = 0;
        this.fov = 0;
    }

    public int getTransformationIndex() {
        return transformationIndex;
    }

    public void setTransformationIndex(int transformationIndex) {
        this.transformationIndex = transformationIndex;
    }

    public double getDistance() {
        return distance;
    }

    public void setDistance(double distance) {
        this.distance = distance;
    }

    public double getFov() {
        return fov;
    }

    public void setFov(double fov) {
        this.fov = fov;
    }

    @Override
    public String toString() {
        return "Camera{" +
                "transformationIndex=" + transformationIndex +
                ", distance=" + distance +
                ", fov=" + fov +
                '}';
    }
}
