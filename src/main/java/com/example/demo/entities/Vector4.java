package com.example.demo.entities;

public class Vector4 {
    private double x;
    private double y;
    private double z;
    private double w;

    public Vector4 (Vector3 vector, double w){
        this.x = vector.getX();
        this.y = vector.getY();
        this.z = vector.getZ();
        if(w > 1) this.w = 1;
        if(w < 0) this.w = 0;
        if(w >= 0 && w <= 1) this.w = w;
    }

    public Vector4(double x, double y, double z, double w) {
        this.x = x;
        this.y = y;
        this.z = z;
        this.w = w;
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public double getZ() {
        return z;
    }

    public double getW() {
        return w;
    }

    public void setX(double x) {
        this.x = x;
    }

    public void setY(double y) {
        this.y = y;
    }

    public void setZ(double z) {
        this.z = z;
    }

    public void setW(double w) {
        this.w = w;
    }

    public static void main(String[] args) {
    }

    public Vector3 getVector3(){
        if(this.w == 0) return new Vector3(x,y,z);
        return new Vector3(x/w, y/w, z/w);
    }
}
