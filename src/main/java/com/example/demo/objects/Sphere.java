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
        // The vector from the sphere's center to the ray's origin
        Vector3 originToCenter = ray.getOrigin().subtract(this.position);

        // Calculate the components needed for the quadratic formula
        double a = ray.getDirection().dot(ray.getDirection());
        double b = 2.0 * originToCenter.dot(ray.getDirection());
        double radius = 1;
        double c = originToCenter.dot(originToCenter) - (radius * radius);

        // Calculate the discriminant
        double discriminant = b * b - 4 * a * c;

        // If discriminant is negative, no real roots and the ray does not intersect the sphere
        if (discriminant < 0) {
            return false;
        }

        // Calculate the two points of intersection t0 and t1 (quadratic formula)
        double sqrtDiscriminant = Math.sqrt(discriminant);
        double t0 = (-b - sqrtDiscriminant) / (2 * a);
        double t1 = (-b + sqrtDiscriminant) / (2 * a);

        // We want the closest positive intersection
        double t = (t0 < t1 && t0 >= 0) ? t0 : t1;

        // If t is negative, the sphere is behind the ray
        if (t < 0) {
            return false;
        }

        // Calculate the point of intersection using the ray's equation
        Vector3 P = ray.getOrigin().add(ray.getDirection().multiply(t));

        // Update hit record with the intersection data
        hit.setT(t);
        hit.setTmin(t); // Note: This should probably be compared with the current hit record tmin
        hit.setPoint(P);
        hit.setMaterial(this.material);
        hit.setNormal(P.subtract(this.position).normalize()); // Normal should be normalized
        hit.setFound(true);

        return true;
    }
}