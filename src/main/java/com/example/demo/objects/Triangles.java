package com.example.demo.objects;
import com.example.demo.entities.Hit;
import com.example.demo.entities.Object3D;
import com.example.demo.entities.Ray;
import com.example.demo.entities.Vector3;

import java.util.ArrayList;
import java.util.List;

// Classe principal: TrianglesSegment
public class Triangles implements Object3D {

    // Campo para transformação
    private int transformation;

    // Lista para armazenar os triângulos
    private List<Triangle> triangles = new ArrayList<>();

    public Triangles(int transformation) {
        this.transformation = transformation;
    }

    // Método para adicionar um triângulo à lista
    public void addTriangle(int material, List<Vector3> vertices) {
        if (vertices.size() % 3 != 0) {
            throw new IllegalArgumentException("The number of vertices must be a multiple of 3 to form complete triangles.");
        }

        for (int i = 0; i < vertices.size(); i += 3) {
            Vector3 v1 = vertices.get(i);
            Vector3 v2 = vertices.get(i + 1);
            Vector3 v3 = vertices.get(i + 2);

            Triangle triangle = new Triangle(material, v1, v2, v3);
            triangles.add(triangle);
        }
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

    @Override
    public boolean intersect(Ray ray, Hit hit) {
        return false;
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
