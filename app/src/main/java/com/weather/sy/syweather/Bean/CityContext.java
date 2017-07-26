package com.weather.sy.syweather.Bean;

import java.io.Serializable;

public class CityContext implements Serializable{
    private String cityName;



    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }
}
