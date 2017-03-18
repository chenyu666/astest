package com.example.smartbj.ui.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;

/**
 * Created by HSAEE on 2017/3/18.
 */
public class HomeTabPage extends TabPage {
    private static final String TAG = "HomeTabPage";
    public HomeTabPage(Context context) {
        super(context);
    }
    public HomeTabPage(Context context, AttributeSet attrs) {
        super(context, attrs);
        Log.d(TAG, "HomeTabPage: ");
    }

}
