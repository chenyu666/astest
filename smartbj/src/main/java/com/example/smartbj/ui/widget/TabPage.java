package com.example.smartbj.ui.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.smartbj.R;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by HSAEE on 2017/3/17.
 */

public class TabPage extends FrameLayout {
    @BindView(R.id.iv_menu)
    ImageView mIvMenu;
    @BindView(R.id.tv_title)
    TextView mTvTitle;
    @BindView(R.id.framelayout)
    FrameLayout mFramelayout;

    public TabPage(Context context) {
        this(context, null);
    }

    public TabPage(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();

    }

    private void init() {
        View.inflate(getContext(), R.layout.view_tab_page, this);
        ButterKnife.bind(this, this);
        mIvMenu.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                mListener.onTabpageClick();
            }
        });
    }

    public void hideMenu() {
        mIvMenu.setVisibility(GONE);
    }

    public void setTitle(String title) {
        mTvTitle.setText(title);
    }


    public interface OnTabpageClickListener {
        void onTabpageClick();
    }

    OnTabpageClickListener mListener;

    public void onMenuChanged(int postion) {
        //Toast.makeText(getContext(), "tabpage"+postion, Toast.LENGTH_SHORT).show();


    }

    public void setOnTabpageClickListener(OnTabpageClickListener listener) {
        mListener = listener;
    }

}
