package com.example.smartbj.ui.widget;

import android.content.Context;
import android.graphics.Color;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.example.smartbj.R;
import com.itheima.pulltorefreshlib.PullToRefreshListView;
import com.leon.loopviewpagerlib.FunBanner;

/**
 * Created by HSAEE on 2017/3/18.
 */

public class NewsPullToRefreshListView extends PullToRefreshListView {

    private int[] imageResIds = {R.mipmap.icon_1, R.mipmap.icon_2, R.mipmap.icon_3, R.mipmap.icon_4, R.mipmap.icon_5};
    public NewsPullToRefreshListView(Context context) {
        this(context, null);
    }

    public NewsPullToRefreshListView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    private void init() {
        setAdapter(mAdapter);
        //设置上拉和下拉刷新 默认上拉刷新
        setMode(Mode.BOTH);
        //集成轮播图 加入listview头部
        FunBanner.Builder builder = new FunBanner.Builder(getContext());
        FunBanner funBanner = builder.setEnableAutoLoop(true)
                .setImageResIds(imageResIds)
                .setDotSelectedColor(Color.BLUE)
                .setHeightWidthRatio(0.5556f)
                .setLoopInterval(5000)
                .setEnableAutoLoop(true)
                .setIndicatorBackgroundColor(R.color.indicator_bg)
                .build();
        //拿到listview
        getRefreshableView().addHeaderView(funBanner);


    }


    private BaseAdapter mAdapter = new BaseAdapter() {
        @Override
        public int getCount() {
            return 30;
        }

        @Override
        public Object getItem(int position) {
            return null;
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            if (convertView == null) {
                convertView = View.inflate(getContext(), R.layout.item_view_news_refreshlist,null);
            }
            return convertView;
        }
    };

}
