package com.weather.sy.syweather.Activity;

import android.content.Intent;
import android.os.Handler;

import com.example.byhieglibrary.Activity.BaseActivity;
import com.weather.sy.syweather.R;
import com.weather.sy.syweather.Service.BackGroundService;

public class StartActivity extends BaseActivity {

    private static final String ACTION_ADD_CITY = "com.weather.sy.syweather.Activity.action.addCity";
    private static final String ACTION_GET_WEATHER = "com.weather.sy.syweather.Activity.action.getWeather";
    private static final String ACTION_START_NOTIFICATION = "com.weather.sy.syweather.Activity.action.notification";
    private static final String ACTION_ADD_VIEWSPOT = "com.weather.sy.syweather.Activity.action.viewspot";
    private static final String ACTION_FILE_PROCESS = "com.weather.sy.syweather.Activity.action.fileprocess";

    @Override
    public void initData() {
        startGetWeatherService();
        startAddCityService();
        startNotificationService();
        startFileProcessService();
    }

    @Override
    public void initEvent() {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                startActivity(MainActivity.class);
                finish();
            }
        },3500);
    }

    @Override
    public void initView() {

    }

    @Override
    public void initTheme() {

    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_start;
    }

    private void startAddCityService(){
        Intent intent = new Intent(this, BackGroundService.class);
        intent.setAction(ACTION_ADD_CITY);
        startService(intent);
    }

    private void startGetWeatherService(){
        Intent intent = new Intent(this, BackGroundService.class);
        intent.setAction(ACTION_GET_WEATHER);
        startService(intent);
    }

    private void startNotificationService(){
        Intent intent = new Intent(this, BackGroundService.class);
        intent.setAction(ACTION_START_NOTIFICATION);
        startService(intent);
    }


    private void startFileProcessService(){
        Intent intent = new Intent(this, BackGroundService.class);
        intent.setAction(ACTION_FILE_PROCESS);
        startService(intent);
    }

    public static String getActionAddCity() {
        return ACTION_ADD_CITY;
    }

    public static String getActionStartNotification() {
        return ACTION_START_NOTIFICATION;
    }

    public static String getActionGetWeather() {
        return ACTION_GET_WEATHER;
    }

    public static String getActionAddViewspot() {
        return ACTION_ADD_VIEWSPOT;
    }


    public static String getActionFileProcess(){
        return ACTION_FILE_PROCESS;
    }
}
