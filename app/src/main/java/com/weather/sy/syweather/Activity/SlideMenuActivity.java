package com.weather.sy.syweather.Activity;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.example.byhieglibrary.Activity.BaseActivity;
import com.weather.sy.syweather.Fragment.CalendarFragment;
import com.weather.sy.syweather.Fragment.FutureFragment;
import com.weather.sy.syweather.Fragment.SettingFragment;
import com.weather.sy.syweather.Fragment.ShareFragment;
import com.weather.sy.syweather.Fragment.TravelplanFragment;
import com.weather.sy.syweather.Fragment.WikiFragment;
import com.weather.sy.syweather.R;
import com.weather.sy.syweather.Tools.Constants;

import butterknife.Bind;

public class SlideMenuActivity extends BaseActivity {

    @Bind(R.id.toolbar)
    public Toolbar toolbar;

    BaseActivity a = this;

    private FragmentManager fm;


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
                fragment = new TravelplanFragment(this);
                fm.beginTransaction()
                        .add(R.id.fragment, fragment, TravelplanFragment.TAG).commit();
                break;
            case Constants.CALENDAR:
                toolbar.setTitle(R.string.calender);
                fragment = new CalendarFragment(this);
                fm.beginTransaction()
                        .add(R.id.fragment, fragment, CalendarFragment.TAG).commit();
                break;
            case Constants.WIKI:
                toolbar.setTitle(R.string.wiki);
                fragment = new WikiFragment();
                fm.beginTransaction()
                        .add(R.id.fragment, fragment,WikiFragment.TAG).commit();
                break;
        }

        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setHomeButtonEnabled(true); //设置返回键可用
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
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
