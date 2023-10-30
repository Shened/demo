package com.example.demo.objects;
import com.example.demo.entities.Hit;
import com.example.demo.entities.Object3D;
import com.example.demo.entities.Ray;
import com.example.demo.entities.Vector3;

import java.util.ArrayList;
import java.util.List;

// Classe principal: TrianglesSegment
public class Triangles extends Object3D {

    // Campo para transformação
    private int transformation;

    // Lista para armazenar os triângulos
    private List<Triangle> trianglesList = new ArrayList<>();

    public Triangles(int transformation) {
        super(transformation);
    }

    // Método para adicionar um triângulo à lista
    public void addTriangle(int materialIndex, Vector3 point1, Vector3 point2, Vector3 point3) {
        trianglesList.add(new Triangle(materialIndex, point1, point2, point3));
    }

    public void setTransformation(int transformation) {
        this.transformation = transformation;
    }

    public List<Triangle> getTriangles() {
        return trianglesList;
    }

    @Override
    public boolean intersect(Ray ray, Hit hit) {
        boolean found = false;
        for (Triangle triangle: trianglesList) {
            Vector3 origin = ray.getOrigin();
            Vector3 direction = ray.getDirection();

            Vector3 A = triangle.getVertex1();
            Vector3 B = triangle.getVertex2();
            Vector3 C = triangle.getVertex3();

            double axrx = A.getX() - origin.getX();
            double ayry = A.getY() - origin.getY();
            double azrz = A.getZ() - origin.getZ();
            double axbx = A.getX() - B.getX();
            double ayby = A.getY() - B.getY();
            double azbz = A.getZ() - B.getZ();
            double axcx = A.getX() - C.getX();
            double aycy = A.getY() - C.getY();
            double azcz = A.getZ() - C.getZ();

            double dx = direction.getX();
            double dy = direction.getY();
            double dz = direction.getZ();

            double[][]matrix_1 = {
                    {axrx, axcx, dx},
                    {ayry, aycy, dy},
                    {azrz, azcz, dz}
            };

            double[][]matrix_a = {
                    {axbx, axcx, dx},
                    {ayby, aycy, dy},
                    {azbz, azcz, dz}
            };

            double determinante_matrix_a = calculateDeterminant(matrix_a);

            double beta = calculateDeterminant(matrix_1) / determinante_matrix_a;

            double infitesimo = Math.pow(10, -6);

            if(beta > -infitesimo){
                double[][]matrix_2 = {
                    {axbx, axrx, dx},
                    {ayby, ayry, dy},
                    {azbz, azrz, dz}
                };

                double gama = calculateDeterminant(matrix_2) / determinante_matrix_a;

                if(gama > -infitesimo && (gama + beta) < (1 + infitesimo)){ //Dentro do triangulo
                    double[][]matrix_3 = {
                            {axbx, axcx, axrx},
                            {ayby, aycy, ayry},
                            {azbz, azcz, azrz}
                    };

                    double t = calculateDeterminant(matrix_3) / determinante_matrix_a;

                    Vector3 P = triangle.getVertex1().add((triangle.getVertex2().subtract(triangle.getVertex3())).multiply(beta)).subtract((triangle.getVertex3().subtract(triangle.getVertex1())).multiply(gama));

                    if(hit.getTmin() > t){
                        hit.setTmin(t);
                        hit.setT(t);
                        hit.setFound(true);
                        hit.setMaterial(triangle.getMaterial());
                        hit.setNormal(triangle.getNormal());
                        hit.setPoint(P);
                        found = true;
                    }
                }
            }
        }
        return found;
    }

    private double calculateDeterminant(double[][] matrix) {
        if (matrix.length != 3 || matrix[0].length != 3) {
            throw new IllegalArgumentException("Matrix must be 3x3");
        }

        double a = matrix[0][0];
        double b = matrix[0][1];
        double c = matrix[0][2];
        double d = matrix[1][0];
        double e = matrix[1][1];
        double f = matrix[1][2];
        double g = matrix[2][0];
        double h = matrix[2][1];
        double i = matrix[2][2];

        return a * e * i + b * f * g + c * d * h - c * e * g - b * d * i - a * f * h;
    }

    // Classe interna: Triangle
    public static class Triangle {

        private Vector3 normal;
        private int material;
        private Vector3 vertex1, vertex2, vertex3;

        public Triangle(int material, Vector3 vertex1, Vector3 vertex2, Vector3 vertex3) {
            this.material = material;
            this.vertex1 = vertex1;
            this.vertex2 = vertex2;
            this.vertex3 = vertex3;
            generateNormal();
        }

        private void generateNormal() {
            Vector3 ab = vertex2.subtract(vertex1);
            Vector3 ac = vertex3.subtract(vertex1);
            this.normal = ab.cross(ac);
            this.normal = this.normal.normalize();
        }

        public Vector3 getNormal() {
            return normal;
        }

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
