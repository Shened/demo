package com.example.demo.entities;

public class Vector3 {
    private double x;
    private double y;
    private double z;

    public Vector3(double x, double y, double z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    // Adição de vetores
    public Vector3 add(Vector3 other) {
        return new Vector3(this.x + other.x, this.y + other.y, this.z + other.z);
    }

    // Subtração de vetores
    public Vector3 subtract(Vector3 other) {
        return new Vector3(this.x - other.x, this.y - other.y, this.z - other.z);
    }

    // Multiplicação por escalar
    public Vector3 multiply(double scalar) {
        return new Vector3(this.x * scalar, this.y * scalar, this.z * scalar);
    }

    // Comprimento (magnitude) do vetor
    public double length() {
        return Math.sqrt(x * x + y * y + z * z);
    }

    public Vector3 normalize() {
        double len = length();
        if (len != 0) {
            return new Vector3(this.x / len, this.y / len, this.z / len);
        } else {
            return new Vector3(0, 0, 0); // Evitar dividir por zero
        }
    }

    public double dot(Vector3 other) {
        return this.x * other.x + this.y * other.y + this.z * other.z;
    }

    public Vector3 cross(Vector3 other) {
        double resultX = this.y * other.z - this.z * other.y;
        double resultY = this.z * other.x - this.x * other.z;
        double resultZ = this.x * other.y - this.y * other.x;
        return new Vector3(resultX, resultY, resultZ);
    }

    public Vector3 plus(Vector3 other) {
        return add(other);
    }

    public Vector3 minus(Vector3 other) {
        return subtract(other);
    }

    public Vector3 times(double scalar) {
        return multiply(scalar);
    }

    public void setX(double x) {
        this.x = x;
    }

    public void setY(double y) {
        this.y = y;
    }

    public void setZ(double z) {
        this.z = z;
    }
}
