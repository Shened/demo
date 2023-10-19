package com.example.demo.objects;

import com.example.demo.entities.Color3;

public class Image {
    private int horRes;
    private int verRes;
    private Color3 backgroundColor;

    public int getHorRes() {
        return horRes;
    }

    public void setHorRes(int horRes) {
        this.horRes = horRes;
    }

    public int getVerRes() {
        return verRes;
    }

    public void setVerRes(int verRes) {
        this.verRes = verRes;
    }

    public Color3 getBackgroundColor() {
        return backgroundColor;
    }

    public void setBackgroundColor(Color3 backgroundColor) {
        this.backgroundColor = backgroundColor;
    }

    public Image(int horRes, int verRes, Color3 backgroundColor) {
        this.horRes = horRes;
        this.verRes = verRes;
        this.backgroundColor = backgroundColor;
    }
    public Image(){
        this.horRes = 0;
        this.verRes = 0;
        this.backgroundColor = new Color3(0,0,0);
    }
}

