package com.example.demo.entities;

import com.example.demo.objects.Material;

public class Hit {
    private boolean found; // Verdadeira se uma interseção foi encontrada
    private int material; // Material do objeto intersectado
    private Vector3 point; // Ponto de interseção
    private Vector3 normal; // Vetor normal ao plano tangente à superfície no ponto de interseção
    private double t; // Distância da origem do raio ao ponto de interseção
    private double tmin; // Valor mínimo da distância t encontrado até o momento

    // Construtor
    public Hit(boolean found, double tmin) {
        this.found = found;
        this.tmin = tmin;
    }

    // Métodos para obter informações do Hit
    public boolean isFound() {
        return found;
    }

    public int getMaterial() {
        return material;
    }

    public Vector3 getPoint() {
        return point;
    }

    public Vector3 getNormal() {
        return normal;
    }

    public double getT() {
        return t;
    }

    public double getTmin() {
        return tmin;
    }

    public void setFound(boolean found) {
        this.found = found;
    }

    public void setMaterial(int material) {
        this.material = material;
    }

    public void setNormal(Vector3 normal) {
        this.normal = normal;
    }

    public void setPoint(Vector3 point) {
        this.point = point;
    }

    public void setT(double t) {
        this.t = t;
    }

    public void setTmin(double tmin) {
        this.tmin = tmin;
    }
}
