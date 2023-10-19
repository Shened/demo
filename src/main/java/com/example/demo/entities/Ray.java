package com.example.demo.entities;

public class Ray {
    private Vector3 origin;
    private Vector3 direction;

    public Ray(Vector3 origin, Vector3 direction) {
        this.origin = origin;
        this.direction = direction.normalize();
    }

    //Pontp de origem do raio
    public Vector3 getOrigin() {
        return origin;
    }

    //Vetor de direção do raio
    public Vector3 getDirection() {
        return direction;
    }

    public void setOrigin(Vector3 origin) {
        this.origin = origin;
    }

    public void setDirection(Vector3 direction) {
        this.direction = direction;
    }


    public static void main(String[] args) {
    }
}
