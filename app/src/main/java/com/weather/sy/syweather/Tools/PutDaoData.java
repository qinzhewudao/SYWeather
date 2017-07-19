package com.weather.sy.syweather.Tools;


import com.weather.sy.syweather.Bean.WeatherBean;
import com.weather.sy.syweather.Db.CityWeather;

import java.io.ByteArrayOutputStream;
import java.io.ObjectOutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;


public class PutDaoData {

    public static CityWeather putWeatherData(WeatherBean weatherBean) throws Exception {
        CityWeather weather = new CityWeather();

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm", Locale.getDefault());
        Date date = simpleDateFormat.parse(MyJson.getWeather(weatherBean).getBasic().getUpdate().getLoc());
        weather.setUpdateTime(date);
        weather.setCityName(weatherBean.getHeWeatherdataservice30().get(0).getBasic().getCity());
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(byteArrayOutputStream);
        objectOutputStream.writeObject(weatherBean);
        objectOutputStream.flush();
        byte[] bytes = byteArrayOutputStream.toByteArray();
        weather.setWeatherBean(bytes);

        return weather;

    }
}
