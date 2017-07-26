package com.weather.sy.syweather.Bean;

import java.io.Serializable;

public class ProvinceContext implements Serializable{
    public int image;
    public String name;

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
