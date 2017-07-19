package com.weather.sy.syweather.Tools;

import com.weather.sy.syweather.Bean.Weather;
import com.weather.sy.syweather.Bean.WeatherBean;

/**
 * Created by byhieg on 16-8-9.
 */
public class MyJson {

    public static Weather getWeather(WeatherBean weatherBean){
        if (weatherBean.getHeWeatherdataservice30().get(0) == null) {
            return null;
        }
        return weatherBean.getHeWeatherdataservice30().get(0);
    }
}
