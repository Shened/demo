package com.example.demo.entities;

public class Color3 {
    private double red;    // Cor vermelha
    private double green;  // Cor verde
    private double blue;   // Cor azul

    public Color3(double red, double green, double blue) {
        // Valores entre [0.0, 1.0]
        this.red = Math.min(Math.max(red, 0.0f), 1.0f);
        this.green = Math.min(Math.max(green, 0.0f), 1.0f);
        this.blue = Math.min(Math.max(blue, 0.0f), 1.0f);
    }

    public Color3(){
        this.red = 0;
        this.green = 0;
        this.blue = 0;
    }

    public void checkRange(){
        this.red = Math.min(Math.max(this.red, 0), 1);
        this.green = Math.min(Math.max(this.green, 0), 1);
        this.blue = Math.min(Math.max(this.blue, 0), 1);
    }

    // Métodos para obter os componentes RGB
    public double getRed() {
        return red;
    }

    public double getGreen() {
        return green;
    }

    public double getBlue() {
        return blue;
    }

    public void setRed(double red) {
        this.red = red;
    }

    public void setGreen(double green) {
        this.green = green;
    }

    public void setBlue(double blue) {
        this.blue = blue;
    }
}
