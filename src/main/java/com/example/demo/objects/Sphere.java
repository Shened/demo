package com.example.demo.objects;

import com.example.demo.entities.*;

public class Sphere extends Object3D {

    // Campos para transformação e material
    private int material;

    private double ray = 1;

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
        double R2 = ray.getOrigin().dot(ray.getOrigin());
        double r2 = (this.ray * this.ray);

        if(R2 <= r2) return false;

        double tp = this.position.subtract(ray.getOrigin()).dot(ray.getDirection());

        if(tp < 0) return false;

        double distance2 = R2 - (tp * tp);

        if(distance2 > r2) return false;

        double t2 = r2 - distance2;

        double t = tp - Math.sqrt(t2);

        if(hit.getTmin() > t){
            Vector3 P = ray.getOrigin().add(ray.getDirection().multiply(t));

            hit.setT(t);
            hit.setTmin(t);
            hit.setPoint(P);
            hit.setMaterial(this.material);
            hit.setNormal(P.subtract(position));
            hit.setFound(true);

            return true;
        }
        return false;
    }
}