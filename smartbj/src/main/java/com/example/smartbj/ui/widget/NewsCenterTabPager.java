package com.example.smartbj.ui.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

/**
 * Created by HSAEE on 2017/3/18.
 */

public class NewsCenterTabPager extends TabPage {
    private static final String TAG = "NewsCenterTabPager";
    public NewsCenterTabPager(Context context) {
        super(context);
    }

    public NewsCenterTabPager(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public void onMenuChanged(int postion) {
        Log.d(TAG, "onMenuChanged: " + postion);
        View view = null;

        //TextView textView = new TextView(getContext());

        switch (postion) {  //全部的判断到实现非常臃肿,将tabpage当做基类,让子类实现onmenuchange方法
            case 0 :
                //textView.setText("新闻");
                NewsPage newsPage = new NewsPage(getContext());
                view = newsPage;
                break;
            case 1:
                TextView textView = new TextView(getContext());
                textView.setText("专题");
                view = textView;
                break;
            case 2:
                TextView zutu = new TextView(getContext());
                zutu.setText("组图");
                view = zutu;
                break;
            case 3:
                TextView hudong = new TextView(getContext());
                hudong.setText("互动");
                view = hudong;
                break;
        }
        mFramelayout.removeAllViews();
        mFramelayout.addView(view);
    }
}
