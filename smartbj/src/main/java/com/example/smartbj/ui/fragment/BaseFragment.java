package com.example.smartbj.ui.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import butterknife.ButterKnife;

/**
 * Created by HSAEE on 2017/3/17.
 */

public abstract class BaseFragment extends Fragment {
    @Nullable
    @Override
    /**
     * 主界面fragment的基类,初始化相同的部分  oncreateview  返回fragment的视图
     */
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(getResLayoutId(), null);
        ButterKnife.bind(this, view);  //将视图view 绑定到fragment
        init();
        return view;
    }

    protected  void init() {};

    public abstract int getResLayoutId();


}
