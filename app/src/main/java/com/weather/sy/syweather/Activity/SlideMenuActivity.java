package com.weather.sy.syweather.Activity;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.example.byhieglibrary.Activity.BaseActivity;
import com.weather.sy.syweather.Fragment.AboutFragment;
import com.weather.sy.syweather.Fragment.FutureFragment;
import com.weather.sy.syweather.Fragment.LaboratoryFragment;
import com.weather.sy.syweather.Fragment.SettingFragment;
import com.weather.sy.syweather.Fragment.ShareFragment;
import com.weather.sy.syweather.Fragment.WikiFragment;
import com.weather.sy.syweather.R;
import com.weather.sy.syweather.Tools.Constants;

import butterknife.Bind;

public class SlideMenuActivity extends BaseActivity {

    @Bind(R.id.toolbar)
    public Toolbar toolbar;

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
            case Constants.LAB:
                toolbar.setTitle(R.string.laboratory);
                fragment = new LaboratoryFragment();
                fm.beginTransaction()
                        .add(R.id.fragment, fragment,LaboratoryFragment.TAG).commit();
                break;
            case Constants.WIKI:
                toolbar.setTitle(R.string.wiki);
                fragment = new WikiFragment();
                fm.beginTransaction()
                        .add(R.id.fragment, fragment,WikiFragment.TAG).commit();
                break;
            case Constants.ABOUT:
                toolbar.setTitle(R.string.about);
                fragment = new AboutFragment();
                fm.beginTransaction()
                        .add(R.id.fragment, fragment,AboutFragment.TAG).commit();
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
