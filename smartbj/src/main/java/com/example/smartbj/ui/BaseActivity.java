package com.example.smartbj.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import butterknife.ButterKnife;

/**
 * Created by HSAEE on 2017/3/16.
 */

public abstract class BaseActivity extends AppCompatActivity  {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutResId());
        //集成butterknife
        ButterKnife.bind(this);

        init();
    }

    protected void init() {

    }

    //强制子类实现这个绑定布局文件的方法
    public abstract int getLayoutResId() ;

    public void navigateTo(Class activity) {
        Intent intent = new Intent(this,activity);
        startActivity(intent);
        finish();
    }

}
