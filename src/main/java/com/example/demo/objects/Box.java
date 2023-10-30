package com.example.demo.objects;

import com.example.demo.entities.Hit;
import com.example.demo.entities.Object3D;
import com.example.demo.entities.Ray;
import com.example.demo.entities.Vector3;

public class Box implements Object3D {

    // Campos para transformação e material

    public double aresta;
    public Vector3 origin;
    private int transformation;
    private int material;

    public Box(int transformation, int material) {
        this.transformation = transformation;
        this.material = material;
        this.aresta = 1;
        this.origin = new Vector3(0,0,0);
    }

    // Getters e Setters para BoxSegment
    public int getTransformation() {
        return transformation;
    }

    public void setTransformation(int transformation) {
        this.transformation = transformation;
    }

    public int getMaterial() {
        return material;
    }

    public void setMaterial(int material) {
        this.material = material;
    }

    @Override
    public boolean intersect(Ray ray, Hit hit) {
        Vector3 origin = ray.getOrigin();
        Vector3 direction = ray.getDirection();

        double x1 = origin.getX() - (aresta/2);
        double x2 = origin.getX() + (aresta/2);
        double y1 = origin.getY() - (aresta/2);
        double y2 = origin.getY() + (aresta/2);
        double z1 = origin.getZ() - (aresta/2);
        double z2 = origin.getZ() + (aresta/2);

        if(direction.getX() == 0 && (origin.getX() < x1 || origin.getX() > x2)) return false;

        if(direction.getY() == 0 && (origin.getY() < y1 || origin.getY() > y2)) return false;

        if(direction.getZ() == 0 && (origin.getZ() < z1 || origin.getZ() > z2)) return false;

        double xt1 = (x1 - origin.getX())/direction.getX();
        double xt2 = (x2 - origin.getX())/direction.getX();
        double yt1 = (y1 - origin.getX())/direction.getX();
        double yt2 = (y2 - origin.getX())/direction.getX();
        double zt1 = (z1 - origin.getX())/direction.getX();
        double zt2 = (z2 - origin.getX())/direction.getX();

        double tnear = -Math.pow(10,12);
        double tfar = Math.pow(10,12);

        if(xt1 > xt2){
            double tprov;
            tprov = xt1;
            xt1 = xt2;
            xt2 = tprov;
        }
        if(yt1 > yt2){
            double tprov;
            tprov = yt1;
            yt1 = yt2;
            yt2 = tprov;
        }
        if(zt1 > zt2){
            double tprov;
            tprov = zt1;
            zt1 = zt2;
            zt2 = tprov;
        }

        if(xt1>tnear) tnear = xt1;

        if(yt1>tnear) tnear = yt1;

        if(zt1>tnear) tnear = zt1;

        if(xt2<tfar) tfar=xt2;
        if(tfar < 0 ) return false;
        if(yt2<tfar) tfar=yt2;
        if(tfar < 0 ) return false;
        if(zt2<tfar) tfar=zt2;
        if(tfar < 0 ) return false;

        if(hit.getTmin() > tnear){

            double intersectionX = ray.getOrigin().getX() + tnear * ray.getDirection().getX();
            double intersectionY = ray.getOrigin().getY() + tnear * ray.getDirection().getY();
            double intersectionZ = ray.getOrigin().getZ() + tnear * ray.getDirection().getZ();
            Vector3 P = new Vector3(intersectionX, intersectionY, intersectionZ);

            hit.setTmin(tnear);
            hit.setT(tnear);
            hit.setFound(true);
            hit.setMaterial(this.material);
            hit.setNormal(calculateNormal(P));
            hit.setPoint(P);
            return true;
        }
        return false;
    }

    public Vector3 calculateNormal(Vector3 intersectionPoint) {
        double epsilon = 1e-6;

        double cubeCentreX = origin.getX();
        double cubeCentreY = origin.getY();
        double cubeCentreZ = origin.getZ();
        double dx = Math.abs(intersectionPoint.getX() - cubeCentreX);
        double dy = Math.abs(intersectionPoint.getY() - cubeCentreY);
        double dz = Math.abs(intersectionPoint.getZ() - cubeCentreZ);

        if (Math.abs(dx - (aresta / 2)) < epsilon) {
            return new Vector3(Math.signum(intersectionPoint.getX() - cubeCentreX), 0, 0);
        } else if (Math.abs(dy - (aresta / 2)) < epsilon) {
            return new Vector3(0, Math.signum(intersectionPoint.getY() - cubeCentreY), 0);
        } else if (Math.abs(dz - (aresta / 2)) < epsilon) {
            return new Vector3(0, 0, Math.signum(intersectionPoint.getZ() - cubeCentreZ));
        } else {
            return new Vector3(0, 0, 0);
        }
    }
}