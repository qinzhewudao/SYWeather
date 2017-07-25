package com.weather.sy.syweather.Service;

import android.app.IntentService;
import android.content.Intent;
import android.os.Handler;
import android.os.Looper;

import com.example.byhieglibrary.Utils.LogUtils;
import com.google.gson.Gson;
import com.weather.sy.syweather.Activity.StartActivity;
import com.weather.sy.syweather.Bean.UrlCity;
import com.weather.sy.syweather.Db.City;
import com.weather.sy.syweather.Db.LoveCity;
import com.weather.sy.syweather.Db.Province;
import com.weather.sy.syweather.MyApplication;
import com.weather.sy.syweather.R;
import com.weather.sy.syweather.Tools.HandleDaoData;
import com.weather.sy.syweather.Tools.NetTool;
import com.weather.sy.syweather.View.MyToast;

import java.io.BufferedReader;
import java.io.File;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


public class BackGroundService extends IntentService {


    public BackGroundService() {
        super("BackGroundService");
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        if (intent != null) {
            final String action = intent.getAction();
            if (StartActivity.getActionGetWeather().equals(action)) {
                getWeatherData();
            } else if (StartActivity.getActionAddCity().equals(action)) {
                try {
                    addCityData();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            } else if (StartActivity.getActionStartNotification().equals(action)) {
                if (MyApplication.notification()) {
                    startService(new Intent(this, NotificationService.class));
                }
            } else if (StartActivity.getActionFileProcess().equals(action)) {
                if (MyApplication.cache()) {
                    deleteCacheFile();
                }
            }
        }
    }

    /**
     * 将所有城市放入数据库
     */
    private void addCityData() throws Exception {
        String[] provinces = {"北京", "天津", "河北", "山西", "山东", "辽宁", "吉林", "黑龙江", "上海", "江苏", "浙江", "安徽", "福建", "江西",
                "河南", "湖北", "湖南", "广东", "广西", "海南", "重庆", "四川", "贵州", "云南", "陕西", "甘肃", "青海", "内蒙古",
                "西藏", "宁夏", "新疆", "香港", "澳门", "台湾"};
        if (!HandleDaoData.isExistInProvince()) {
            for (String item : provinces) {
                Province province = new Province();
                province.setProvinceName(item);
                HandleDaoData.insertProvince(province);
            }
        }


        if (!HandleDaoData.isExistInCity()) {
            Gson gson = new Gson();
            InputStream inputStream = getResources().openRawResource(R.raw.city);
            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
            StringBuilder sb = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                sb.append(line);
            }
            UrlCity urlCity = gson.fromJson(sb.toString(), UrlCity.class);
            for (int i = 0; i < urlCity.getCity_info().size(); i++) {
                City city = new City();
                city.setProvinceName(urlCity.getCity_info().get(i).getProv());
                city.setCitynName(urlCity.getCity_info().get(i).getCity());
                city.setLove("no");
                HandleDaoData.insertCity(city);
            }
        }

    }

//    private void addViewSpot() throws IOException {
//        if (!HandleDaoData.isExistInViewSpot()) {
//            Gson gson = new Gson();
//            InputStream inputStream = getResources().openRawResource(R.raw.viewspot);
//            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
//            StringBuilder sb = new StringBuilder();
//            String line;
//            while ((line = reader.readLine()) != null) {
//                sb.append(line);
//            }
//
//            UrlViewSpot urlViewSpot = gson.fromJson(sb.toString(), UrlViewSpot.class);
//            for(int i = 0 ;i < urlViewSpot.getCity_info().size();i++) {
//                ViewSpot viewSpot = new ViewSpot();
//                viewSpot.setLove("no");
//                viewSpot.setDirectName(urlViewSpot.getCity_info().get(i).getCnty());
//                viewSpot.setViewSpotID(urlViewSpot.getCity_info().get(i).getId());
//                viewSpot.setViewSpotName(urlViewSpot.getCity_info().get(i).getCity());
//                HandleDaoData.insertViewSpot(viewSpot);
//            }
//        }
//
//    }

    /**
     * 获取天气
     * <p/>
     * 先检测喜欢城市的数据库是否为空，是的话，添加默认城市成都。
     * 查询天气数据库，必要时进行网络请求
     */
    private void getWeatherData() {
        List<LoveCity> city = HandleDaoData.getLoveCity();
        if (city != null) {
            if (city.size() == 0) {
                LoveCity chengdu = new LoveCity();
                chengdu.setCitynName("成都");
                chengdu.setOrder(1);
                HandleDaoData.insertLoveCity(chengdu);
            }
        }
        LoveCity firstCity = HandleDaoData.getLoveCity(1);
        if (firstCity != null) {
            final List<LoveCity> cityList = HandleDaoData.getLoveCity();
            ExecutorService singleThreadExecutor = Executors.newSingleThreadExecutor();
            for (int i = 0; i < cityList.size(); i++) {
                final int index = i;
                singleThreadExecutor.execute(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            NetTool.doNetWeather(cityList.get(index).getCitynName());
                        } catch (Exception e) {
                            Handler handler = new Handler(Looper.getMainLooper());
                            handler.post(new Runnable() {
                                @Override
                                public void run() {
                                    MyToast myToast = MyToast.createMyToast();
                                    myToast.ToastShow(BackGroundService.this, "网络异常,请检查网络");
                                }
                            });
                        }

                    }
                });
            }
        }

    }

    private void deleteCacheFile() {
        File dir = MyApplication.getAppContext().getExternalFilesDir(null);
        LogUtils.e("fileDir", dir.getAbsolutePath());
        File[] children = dir.listFiles();
        for (int i = 0; i < children.length; i++) {
//            LogUtils.e("children", children[i]);
//            File tmp = new File(children[i]);
            if (children[i].exists() && children[i].isFile()) {
                LogUtils.e("exist", "文件存在");
                if (children[i].delete()) {
                    LogUtils.e("delete", "删除成功");
                }
            }else{
                LogUtils.e("no exist","文件不存在");
            }

        }
    }
}


