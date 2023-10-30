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

    }
}