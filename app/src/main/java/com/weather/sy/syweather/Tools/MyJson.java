package com.weather.sy.syweather.Tools;

import com.weather.sy.syweather.Bean.Weather;
import com.weather.sy.syweather.Bean.WeatherBean;

public class MyJson {

    public static Weather getWeather(WeatherBean weatherBean){
        if (weatherBean.getHeWeatherdataservice30().get(0) == null) {
            return null;
        }
        return weatherBean.getHeWeatherdataservice30().get(0);
    }
}
