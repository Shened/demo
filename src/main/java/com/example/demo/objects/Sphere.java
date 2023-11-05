package com.example.demo.objects;

import com.example.demo.entities.*;

public class Sphere extends Object3D {

    // Campos para transformação e material
    private int material;

    private Vector3 position;

    public Sphere(int transformation, int material) {
        super(transformation);
        this.material = material;
        this.position = new Vector3(0,0,0);
    }

    public int getMaterial() {
        return material;
    }

    public void setMaterial(int material) {
        this.material = material;
    }


    @Override
    public boolean intersect(Ray ray, Hit hit) {
        Vector3 originToCenter = ray.getOrigin().subtract(this.position);

        double a = ray.getDirection().dot(ray.getDirection());
        double b = 2.0 * originToCenter.dot(ray.getDirection());
        double radius = 1;
        double c = originToCenter.dot(originToCenter) - (radius * radius);

        double discriminant = b * b - 4 * a * c;

        if (discriminant < 0) {
            return false;
        }

        double sqrtDiscriminant = Math.sqrt(discriminant);
        double t0 = (-b - sqrtDiscriminant) / (2 * a);
        double t1 = (-b + sqrtDiscriminant) / (2 * a);

        double t = (t0 < t1 && t0 >= 0) ? t0 : t1;

        if (t < 0) {
            return false;
        }

        Vector3 P = ray.getOrigin().add(ray.getDirection().multiply(t));


        hit.setT(t);
        hit.setTmin(t);
        hit.setPoint(P);
        hit.setMaterial(this.material);
        hit.setNormal(P.subtract(this.position).normalize());
        hit.setFound(true);

        return true;
    }
}