package com.weather.sy.syweather.Fragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.byhieglibrary.Activity.BaseActivity;
import com.example.byhieglibrary.Activity.BaseFragment;
import com.weather.sy.syweather.MyApplication;
import com.weather.sy.syweather.R;

import xql.Tool.FileTool;
import xql.activity.ADDDouteActivity;
import xql.routeinfo.AllInfo;
import xql.routeinfo.DayInfo;
import xql.routeinfo.Info;
import xql.view.ScheduleView;

public class CalendarFragment extends BaseFragment{

    private static String yearKey  = "year";
    private static String monthKey = "month";
    private static String daykey   = "day";
    private static String hourkey  = "hour";
    private static String minuteKey = "minute";
    private static String dataKey  = "data";
    BaseActivity a;
    private ScheduleView scheduleView;

    public static final String TAG = "CalendarFragment";

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        this.a = (BaseActivity)context;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        a.setTheme(R.style.DayTheme);
        if (MyApplication.nightMode2()) {
            initNightView(R.layout.night_mode_overlay);
        }

        View view = inflater.inflate(R.layout.fragment_calendar, container, false);

        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        scheduleView = (ScheduleView) view.findViewById(R.id.schedule_view);

        scheduleView.setAddRouteListener(new ScheduleView.AddRouteListener() {
            @Override
            public void addRoute(View view) throws Exception {
                if(view == null){
                    Toast.makeText(a.getApplicationContext(),"请选择日期",Toast.LENGTH_SHORT).show();
                    return;
                }
                Intent intent = new Intent(a.getApplicationContext(), ADDDouteActivity.class);
                Bundle bundle = new Bundle();
                bundle.putInt(yearKey,scheduleView.getCurrentYear());
                bundle.putInt(monthKey,scheduleView.getCurrentMonth());
                bundle.putInt(daykey,scheduleView.getSelectDay());

                intent.putExtras(bundle);
                //跳转到添加事件
                getActivity().startActivityForResult(intent,10);

            }
        });

    }
/*
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        System.out.println("--->"+requestCode+" "+resultCode);
        if(requestCode == 100 && resultCode == 0){
            System.out.println("--->+"+requestCode+" "+resultCode+data);
            Bundle bundle = data.getExtras();
            int year = bundle.getInt(yearKey);
            int month = bundle.getInt(monthKey);
            int day = bundle.getInt(daykey);
            int hour = bundle.getInt(hourkey);
            int minute = bundle.getInt(minuteKey);
            String mydata = bundle.getString(dataKey);
            System.out.println("--->my"+year+" "+month+" "+day+" "+hour+" "+minute+" "+mydata);
            try {
                saveRouteData(year,month,day,hour,minute,mydata);
                scheduleView.mySelect(year,month,day);
                System.out.println("--->++"+requestCode+" "+resultCode);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }*/

    public void setSchedule(Intent intent) throws Exception {
        //添加事件
        Bundle bundle1 = intent.getExtras();
        int year = bundle1.getInt(yearKey);
        int month = bundle1.getInt(monthKey);
        int day = bundle1.getInt(daykey);
        int hour = bundle1.getInt(hourkey);
        int minute = bundle1.getInt(minuteKey);
        String mydata = bundle1.getString(dataKey);
        if(bundle1.getString(dataKey) != null)
        {

            saveRouteData(year,month,day,hour,minute,mydata);

        }
        scheduleView.mySelect(year,month,day);
        Log.e("hahaha","我在抢占资源");
    }
    //保存文件
    private void saveRouteData(int year,int month,int day
            ,int hour,int minute,String data) throws Exception {

        String infoKey = FileTool.getInfoKey(hour,minute);
        String dayinfoKey = FileTool.getDayInfoKey(year,month,day);

        AllInfo infos = FileTool.getAllInfo(a.getApplicationContext());
        if(infos == null){infos = new AllInfo();}

        DayInfo dayInfo = infos.getDayRouteList(dayinfoKey);
        if(dayInfo == null){
            dayInfo = new DayInfo();
        }
        Info info = dayInfo.getInfo(infoKey);
        if(info == null){
            info = new Info();   //创建一个Info存储行程
        }
        info.setYear(year); info.setMonth(month); info.setDay(day);
        info.setHour(hour);info.setMinute(minute);
        info.setData(data);

        System.out.println("--->info.setData(data);"+info.getData());

        dayInfo.addInfo(infoKey,info);
        infos.addDayRouteList(dayinfoKey,dayInfo);

        FileTool.writeAllInfo(a.getApplicationContext(),infos);
    }
}
