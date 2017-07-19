package com.weather.sy.syweather.Fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.byhieglibrary.Activity.BaseFragment;
import com.example.byhieglibrary.Utils.DateUtil;
import com.weather.sy.syweather.Adapter.FutureListAdapter;
import com.weather.sy.syweather.Bean.FutureContext;
import com.weather.sy.syweather.Bean.WeatherBean;
import com.weather.sy.syweather.MyApplication;
import com.weather.sy.syweather.R;
import com.weather.sy.syweather.Tools.HandleDaoData;
import com.weather.sy.syweather.Tools.MyJson;

import java.text.SimpleDateFormat;
import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class FutureFragment extends BaseFragment {

    public static final String TAG = "FutureFragment";
    private ArrayList<FutureContext> lists = new ArrayList<>();
    private FutureListAdapter adapter;

    public FutureFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        getActivity().setTheme(R.style.DayTheme);
        if (MyApplication.nightMode2()) {
            initNightView(R.layout.night_mode_overlay);
        }
        View view = inflater.inflate(R.layout.fragment_future, container, false);
        initView(view);
        return view;
    }

    private void initView(View view) {
        try {
            initData();
        } catch (Exception e) {
            e.printStackTrace();
        }
        RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.future_recycler_view);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);
    }

    private void initData() throws Exception {
        WeatherBean weatherBean = HandleDaoData.getWeatherBean(HandleDaoData.getShowCity());
        String[] weeks = DateUtil.
                getNextWeek(new SimpleDateFormat("yyyy-MM-dd").
                        parse(MyJson.getWeather(weatherBean).getDaily_forecast().get(0).getDate()));

        for(int i = 0;i < MyJson.getWeather(weatherBean).getDaily_forecast().size();i++) {
            FutureContext fc = new FutureContext();
            fc.setCond(MyJson.getWeather(weatherBean).getDaily_forecast().get(i).getCond().getTxt_d());
            fc.setHum(MyJson.getWeather(weatherBean).getDaily_forecast().get(i).getHum());
            fc.setTmp(MyJson.getWeather(weatherBean).getDaily_forecast().get(i).getTmp().getMax() + "°" + "/" +
                    MyJson.getWeather(weatherBean).getDaily_forecast().get(i).getTmp().getMin() + "°");

            fc.setWind(MyJson.getWeather(weatherBean).getDaily_forecast().get(i).getWind().getSpd());
            fc.setVis(MyJson.getWeather(weatherBean).getDaily_forecast().get(i).getVis());
            fc.setPop(MyJson.getWeather(weatherBean).getDaily_forecast().get(i).getPop());
            fc.setSunrise(MyJson.getWeather(weatherBean).getDaily_forecast().get(i).getAstro().getSr());
            fc.setSunset(MyJson.getWeather(weatherBean).getDaily_forecast().get(i).getAstro().getSs());
            fc.setPcpn(MyJson.getWeather(weatherBean).getDaily_forecast().get(i).getPcpn());
            fc.setPres(MyJson.getWeather(weatherBean).getDaily_forecast().get(i).getPres());
            fc.setDes(MyJson.getWeather(weatherBean).getDaily_forecast().get(i).getWind().getDir());
            fc.setTime(weeks[i]);
            lists.add(fc);
        }

        adapter = new FutureListAdapter(lists);
    }


}
