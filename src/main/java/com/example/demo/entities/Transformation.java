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

    public Transformation(int ID, double[][] transformMatrix){
        this.ID = ID;
        this.transformMatrix = transformMatrix;
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

    public double[][] multiplyMatrix(double[][] matrix) {
        int rows1 = transformMatrix.length;
        int cols1 = transformMatrix[0].length;
        int cols2 = matrix[0].length;

        if (cols1 != matrix.length) {
            throw new IllegalArgumentException("Matrix dimensions are not compatible for multiplication.");
        }

        double[][] result = new double[rows1][cols2];

        for (int i = 0; i < rows1; i++) {
            for (int j = 0; j < cols2; j++) {
                double sum = 0.0;
                for (int k = 0; k < cols1; k++) {
                    sum += transformMatrix[i][k] * matrix[k][j];
                }
                result[i][j] = sum;
            }
        }

        return result;
    }

    public double[][] getTransformMatrix() {
        return transformMatrix;
    }

    public void invertMatrix() {
        double[][] matrix = transformMatrix;
        int n = matrix.length;
        double[][] augmentedMatrix = new double[n][2 * n];

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                augmentedMatrix[i][j] = matrix[i][j];
                augmentedMatrix[i][j + n] = (i == j) ? 1.0 : 0.0;
            }
        }

        for (int i = 0; i < n; i++) {
            int pivotRow = i;
            for (int j = i + 1; j < n; j++) {
                if (Math.abs(augmentedMatrix[j][i]) > Math.abs(augmentedMatrix[pivotRow][i])) {
                    pivotRow = j;
                }
            }

            double[] temp = augmentedMatrix[i];
            augmentedMatrix[i] = augmentedMatrix[pivotRow];
            augmentedMatrix[pivotRow] = temp;

            double pivot = augmentedMatrix[i][i];

            for (int j = 0; j < 2 * n; j++) {
                augmentedMatrix[i][j] /= pivot;
            }

            for (int j = 0; j < n; j++) {
                if (j != i) {
                    double factor = augmentedMatrix[j][i];
                    for (int k = 0; k < 2 * n; k++) {
                        augmentedMatrix[j][k] -= factor * augmentedMatrix[i][k];
                    }
                }
            }
        }

        for (int i = 0; i < n; i++) {
            System.arraycopy(augmentedMatrix[i], n, matrix[i], 0, n);
        }
    }

    public void transposeMatrix() {
        int rows = this.transformMatrix.length;
        int cols = this.transformMatrix[0].length;
        double[][] transposedMatrix = new double[cols][rows];

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                transposedMatrix[j][i] = this.transformMatrix[i][j];
            }
        }

        this.transformMatrix = transposedMatrix;
    }
}




