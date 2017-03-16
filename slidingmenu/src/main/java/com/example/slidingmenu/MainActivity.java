package com.example.slidingmenu;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private SlidingMenu mSlidingMenu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mSlidingMenu = (SlidingMenu) findViewById(R.id.slid_view);
    }

    public void onTabClick(View view) {
        mSlidingMenu.close();
        TextView tv = (TextView) view;
        Toast.makeText(this,tv.getText() , Toast.LENGTH_SHORT).show();
    }
}
