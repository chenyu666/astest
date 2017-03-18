package com.example.smartbj.ui.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;

/**
 * Created by HSAEE on 2017/3/18.
 */
public class GovAffairsTabPage extends TabPage {
    private static final String TAG = "GovAffairsTabPage";
    public GovAffairsTabPage(Context context) {
        super(context);
    }
    public GovAffairsTabPage(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public void onMenuChanged(int postion) {
        super.onMenuChanged(postion);
        Log.d(TAG, "onMenuChanged: ");
    }
}
