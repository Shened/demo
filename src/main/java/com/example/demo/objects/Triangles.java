package com.example.demo.objects;
import com.example.demo.entities.Vector3;

import java.util.ArrayList;
import java.util.List;

// Classe principal: TrianglesSegment
public class Triangles {

    // Campo para transformação
    private int transformation;

    // Lista para armazenar os triângulos
    private List<Triangle> triangles = new ArrayList<>();

    public Triangles(int transformation) {
        this.transformation = transformation;
    }

    // Método para adicionar um triângulo à lista
    public void addTriangle(int material, double x1, double y1, double z1,
                            double x2, double y2, double z2,
                            double x3, double y3, double z3) {
        Triangle triangle = new Triangle(material, new Vector3(x1, y1, z1),
                new Vector3(x2, y2, z2),
                new Vector3(x3, y3, z3));
        triangles.add(triangle);
    }

    // Getters e Setters para TrianglesSegment
    public int getTransformation() {
        return transformation;
    }

    public void setTransformation(int transformation) {
        this.transformation = transformation;
    }

    public List<Triangle> getTriangles() {
        return triangles;
    }

    // Classe interna: Triangle
    public static class Triangle {

        private int material;
        private Vector3 vertex1, vertex2, vertex3;

        public Triangle(int material, Vector3 vertex1, Vector3 vertex2, Vector3 vertex3) {
            this.material = material;
            this.vertex1 = vertex1;
            this.vertex2 = vertex2;
            this.vertex3 = vertex3;
        }

        // Getters e Setters para Triangle
        public int getMaterial() {
            return material;
        }

        public void setMaterial(int material) {
            this.material = material;
        }

        public Vector3 getVertex1() {
            return vertex1;
        }

        public void setVertex1(Vector3 vertex1) {
            this.vertex1 = vertex1;
        }

        public Vector3 getVertex2() {
            return vertex2;
        }

        public void setVertex2(Vector3 vertex2) {
            this.vertex2 = vertex2;
        }

        public Vector3 getVertex3() {
            return vertex3;
        }

        public void setVertex3(Vector3 vertex3) {
            this.vertex3 = vertex3;
        }
    }
}
