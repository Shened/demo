package com.example.demo.entities;
import java.lang.Math;

import java.util.Scanner;

public class Transformation {

    private final int ID;
    private double[][] transformMatrix = new double[4][4];

    public Transformation(int ID){
        this.ID = ID;
        identityMatrix();
    }
    public static void main(String[] args) {
        Transformation transformation = new Transformation(0);
        transformation.translate(0.0, 0.0, -74.0);
        /*transformation.rotateX(-60.0);
        transformation.rotateZ(45.0);*/
        Vector4 vector4 = new Vector4(1,1,1,1);
        Vector3 vector3 = transformation.applyTransformation(vector4);

        System.out.println(vector3);
    }

    public Vector3 applyTransformation(Vector4 vector){
        double[] pointA = new double[4];
        double[] pointB = new double[4];
        pointA[0] = vector.getX();
        pointA[1] = vector.getY();
        pointA[2] = vector.getZ();
        pointA[3] = vector.getW();
        multiply1(pointA, pointB);

        if (pointB[3] == 0) return new Vector3(pointB[0], pointB[1], pointB[2]);
        else return new Vector3(pointB[0] / pointB[3], pointB[1] / pointB[3], pointB[2] / pointB[3]);
    }

    public void multiply1(double[] pointA, double[] pointB) {
        for (int i = 0; i < 4; i++) {
            pointB[i] = 0.0;
        }
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                pointB[i] += transformMatrix[i][j] * pointA[j];
            }
        }
    }

    public void multiply3(double[][] matrixA) {
        double[][] matrixB = new double[4][4];
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                matrixB[i][j] = transformMatrix[i][j];
                transformMatrix[i][j] = 0.0;
            }
        }
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                for (int k = 0; k < 4; k++) {
                    transformMatrix[i][j] += matrixB[i][k] * matrixA[k][j];
                }
            }
        }
    }

    public void identityMatrix() {
        transformMatrix[0][0] = 1.0;
        transformMatrix[0][1] = 0.0;
        transformMatrix[0][2] = 0.0;
        transformMatrix[0][3] = 0.0;
        transformMatrix[1][0] = 0.0;
        transformMatrix[1][1] = 1.0;
        transformMatrix[1][2] = 0.0;
        transformMatrix[1][3] = 0.0;
        transformMatrix[2][0] = 0.0;
        transformMatrix[2][1] = 0.0;
        transformMatrix[2][2] = 1.0;
        transformMatrix[2][3] = 0.0;
        transformMatrix[3][0] = 0.0;
        transformMatrix[3][1] = 0.0;
        transformMatrix[3][2] = 0.0;
        transformMatrix[3][3] = 1.0;
    }

    public void translate(double x, double y, double z) {
        double[][] translateMatrix = new double[4][4];
        translateMatrix[0][0] = 1.0;
        translateMatrix[0][1] = 0.0;
        translateMatrix[0][2] = 0.0;
        translateMatrix[0][3] = x;
        translateMatrix[1][0] = 0.0;
        translateMatrix[1][1] = 1.0;
        translateMatrix[1][2] = 0.0;
        translateMatrix[1][3] = y;
        translateMatrix[2][0] = 0.0;
        translateMatrix[2][1] = 0.0;
        translateMatrix[2][2] = 1.0;
        translateMatrix[2][3] = z;
        translateMatrix[3][0] = 0.0;
        translateMatrix[3][1] = 0.0;
        translateMatrix[3][2] = 0.0;
        translateMatrix[3][3] = 1.0;
        multiply3(translateMatrix);
    }

    public void rotateX(double a) {
        double[][] rotateXMatrix = new double[4][4];
        a *= Math.PI / 180.0;
        rotateXMatrix[0][0] = 1.0;
        rotateXMatrix[0][1] = 0.0;
        rotateXMatrix[0][2] = 0.0;
        rotateXMatrix[0][3] = 0.0;
        rotateXMatrix[1][0] = 0.0;
        rotateXMatrix[1][1] = Math.cos(a);
        rotateXMatrix[1][2] = -Math.sin(a);
        rotateXMatrix[1][3] = 0.0;
        rotateXMatrix[2][0] = 0.0;
        rotateXMatrix[2][1] = Math.sin(a);
        rotateXMatrix[2][2] = Math.cos(a);
        rotateXMatrix[2][3] = 0.0;
        rotateXMatrix[3][0] = 0.0;
        rotateXMatrix[3][1] = 0.0;
        rotateXMatrix[3][2] = 0.0;
        rotateXMatrix[3][3] = 1.0;
        multiply3(rotateXMatrix);
    }

    public void rotateY(double a) {
        double[][] rotateYMatrix = new double[4][4];
        a *= Math.PI / 180.0;
        rotateYMatrix[0][0] = Math.cos(a);
        rotateYMatrix[0][1] = 0.0;
        rotateYMatrix[0][2] = Math.sin(a);
        rotateYMatrix[0][3] = 0.0;
        rotateYMatrix[1][0] = 0.0;
        rotateYMatrix[1][1] = 1.0;
        rotateYMatrix[1][2] = 0.0;
        rotateYMatrix[1][3] = 0.0;
        rotateYMatrix[2][0] = -Math.sin(a);
        rotateYMatrix[2][1] = 0.0;
        rotateYMatrix[2][2] = Math.cos(a);
        rotateYMatrix[2][3] = 0.0;
        rotateYMatrix[3][0] = 0.0;
        rotateYMatrix[3][1] = 0.0;
        rotateYMatrix[3][2] = 0.0;
        rotateYMatrix[3][3] = 1.0;
        multiply3(rotateYMatrix);
    }

    public void rotateZ(double a) {
        double[][] rotateZMatrix = new double[4][4];
        a *= Math.PI / 180.0;
        rotateZMatrix[0][0] = Math.cos(a);
        rotateZMatrix[0][1] = -Math.sin(a);
        rotateZMatrix[0][2] = 0.0;
        rotateZMatrix[0][3] = 0.0;
        rotateZMatrix[1][0] = Math.sin(a);
        rotateZMatrix[1][1] = Math.cos(a);
        rotateZMatrix[1][2] = 0.0;
        rotateZMatrix[1][3] = 0.0;
        rotateZMatrix[2][0] = 0.0;
        rotateZMatrix[2][1] = 0.0;
        rotateZMatrix[2][2] = 1.0;
        rotateZMatrix[2][3] = 0.0;
        rotateZMatrix[3][0] = 0.0;
        rotateZMatrix[3][1] = 0.0;
        rotateZMatrix[3][2] = 0.0;
        rotateZMatrix[3][3] = 1.0;
        multiply3(rotateZMatrix);
    }

    public void scale(double x, double y, double z) {
        double[][] scaleMatrix = new double[4][4];
        scaleMatrix[0][0] = x;
        scaleMatrix[0][1] = 0.0;
        scaleMatrix[0][2] = 0.0;
        scaleMatrix[0][3] = 0.0;
        scaleMatrix[1][0] = 0.0;
        scaleMatrix[1][1] = y;
        scaleMatrix[1][2] = 0.0;
        scaleMatrix[1][3] = 0.0;
        scaleMatrix[2][0] = 0.0;
        scaleMatrix[2][1] = 0.0;
        scaleMatrix[2][2] = z;
        scaleMatrix[2][3] = 0.0;
        scaleMatrix[3][0] = 0.0;
        scaleMatrix[3][1] = 0.0;
        scaleMatrix[3][2] = 0.0;
        scaleMatrix[3][3] = 1.0;
        multiply3(scaleMatrix);
    }
}




