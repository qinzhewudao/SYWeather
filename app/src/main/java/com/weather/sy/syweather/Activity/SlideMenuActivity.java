package com.weather.sy.syweather.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.Toolbar;
import android.text.format.Time;
import android.util.Log;
import android.view.View;

import com.example.byhieglibrary.Activity.BaseActivity;
import com.weather.sy.syweather.Fragment.CalendarFragment;
import com.weather.sy.syweather.Fragment.FutureFragment;
import com.weather.sy.syweather.Fragment.SettingFragment;
import com.weather.sy.syweather.Fragment.ShareFragment;
import com.weather.sy.syweather.R;
import com.weather.sy.syweather.Service.NotificationService;
import com.weather.sy.syweather.Tools.Constants;

import butterknife.Bind;
import xql.activity.ADDDouteActivity;
import xql.view.ScheduleView;

public class SlideMenuActivity extends BaseActivity {

    @Bind(R.id.toolbar)
    public Toolbar toolbar;
    private FragmentManager fm;
    private  CalendarFragment transportdata;


    @Override
    public int getLayoutId() {
        return R.layout.activity_laboratory;
    }

    @Override
    public void initData() {
        fm = getSupportFragmentManager();
    }
    @Override
    public void initView() {

        int position = getIntent().getIntExtra("itemId", 0);
        switch (position){
            case Constants.FUTURE:
                toolbar.setTitle(R.string.future);
                Fragment fragment = new FutureFragment();
                fm.beginTransaction()
                        .add(R.id.fragment, fragment,FutureFragment.TAG).commit();
                break;
            case Constants.SETTING:
                toolbar.setTitle(R.string.setting);
                fragment = new SettingFragment();
                fm.beginTransaction()
                        .add(R.id.fragment, fragment,SettingFragment.TAG).commit();
                break;
            case Constants.SHARE:
                toolbar.setTitle(R.string.share);
                fragment = new ShareFragment();
                fm.beginTransaction()
                        .add(R.id.fragment, fragment,ShareFragment.TAG).commit();
                break;
            case Constants.TRAVELPLAN:
                toolbar.setTitle(R.string.travelplan);
                try
                {
                    transportdata = new CalendarFragment(this);
                    Intent intent = new Intent(this.getApplicationContext(), ADDDouteActivity.class);
                    Bundle bundle = new Bundle();
                    ScheduleView scheduleView =  (ScheduleView) findViewById(R.id.schedule_view);
                    String yearKey  = "year";
                    String monthKey = "month";
                    String daykey   = "day";

                    Time t=new Time(); // or Time t=new Time("GMT+8"); 加上Time Zone资料
                    t.setToNow(); // 取得系统时间。
                    int year = t.year;
                    int month = t.month;
                    int date = t.monthDay;
                    bundle.putInt(yearKey,year);
                    bundle.putInt(monthKey,month+1);
                    bundle.putInt(daykey,date);

                    intent.putExtras(bundle);
                    Log.e("saja","我开始跳转了");
                    startActivityForResult(intent,20);
                }catch (Exception e)
                {
                    Log.e("jhaha","出行计划有问题："+e);
                }

                break;
            case Constants.CALENDAR:
                toolbar.setTitle(R.string.calender);
                transportdata = new CalendarFragment(this);
                fm.beginTransaction()
                        .add(R.id.fragment, transportdata, CalendarFragment.TAG).commit();
                break;
            case Constants.WIKI:

                //停止了notification服务
                Intent i = new Intent();
                i.setClass(this, NotificationService.class);
                this.stopService(i);
                //彻底关闭application
                Intent startMain = new Intent(Intent.ACTION_MAIN);
                startMain.addCategory(Intent.CATEGORY_HOME);
                startMain.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(startMain);
                System.exit(0);

                break;
        }

        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setHomeButtonEnabled(true); //设置返回键可用
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Log.e("hahaha","我们得到请求码了"+requestCode+"返回码："+resultCode);
        if(requestCode == 10 && resultCode == 2){
            Log.e("hahaha","我们准备添加事件了");
            try {
                transportdata.setSchedule(data);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        else if(requestCode == 20 && resultCode == 2){
            try {
                transportdata.setSchedule(data);
                toolbar.setTitle(R.string.calender);
                transportdata = new CalendarFragment(this);
                fm.beginTransaction()
                        .add(R.id.fragment, transportdata, CalendarFragment.TAG).commit();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        else if(requestCode == 20 && resultCode == 10){
                toolbar.setTitle(R.string.calender);
                transportdata = new CalendarFragment(this);
                fm.beginTransaction()
                        .add(R.id.fragment, transportdata, CalendarFragment.TAG).commit();
        }
    }

    @Override
    public void initEvent() {
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }


    @Override
    public void initTheme() {

    }

}
