package com.weather.sy.syweather.Bean;

import java.io.Serializable;

public class DrawerContext implements Serializable{
    public int image;
    public int name;

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }

    public int getName() {
        return name;
    }

    public void setName(int name) {
        this.name = name;
    }
}
