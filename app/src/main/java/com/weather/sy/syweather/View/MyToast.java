package com.weather.sy.syweather.View;

import android.content.Context;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.weather.sy.syweather.MyApplication;
import com.weather.sy.syweather.R;

public class MyToast {
    private static MyToast myToast;
    private Toast toast;
    private ViewGroup viewGroup;

    private MyToast(){

    }
    public static MyToast createMyToast(){
        if (myToast == null) {
            myToast = new MyToast();
        }

        return myToast;
    }

    public void ToastShow(Context context, String string) {
        viewGroup = new ViewGroup(MyApplication.getAppContext()) {
            @Override
            protected void onLayout(boolean changed, int l, int t, int r, int b) {

            }
        };
        View layout = LayoutInflater.from(context).inflate(R.layout.layout_toast,viewGroup,false);
        TextView textView = (TextView) layout.findViewById(R.id.toast_text);
        ImageView img = (ImageView) layout.findViewById(R.id.toast_img);
        textView.setText(string);
        img.setImageResource(R.mipmap.ic_sentiment_dissatisfied_black_24dp);
        if(toast == null){
            toast = new Toast(context);
        }
        toast.setGravity(Gravity.BOTTOM, 0, 50);
        toast.setDuration(Toast.LENGTH_LONG);
        toast.setView(layout);
        toast.show();
    }
}
