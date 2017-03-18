package com.example.smartbj.ui.widget;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import com.example.smartbj.R;
import com.viewpagerindicator.TabPageIndicator;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by HSAEE on 2017/3/18.
 */

public class NewsPage extends FrameLayout {

    private static final String[] CONTENT = new String[]{"Recent", "Artists", "Albums", "Songs", "Playlists", "Genres", "Itheima", "HelloKitty"};

    @BindView(R.id.pager)
    ViewPager mPager;
    @BindView(R.id.indicator)
    TabPageIndicator mIndicator;


    public NewsPage(Context context) {
        this(context, null);
    }

    public NewsPage(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    private void init() {
        View.inflate(getContext(), R.layout.view_news_page, this);
        ButterKnife.bind(this, this);
        mPager.setAdapter(mAdapter);
        mIndicator.setViewPager(mPager);
    }


    private PagerAdapter mAdapter = new PagerAdapter() {
        @Override
        public int getCount() {
            return CONTENT.length;
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == object;
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
           /* TextView textView = new TextView(getContext());
            textView.setText(CONTENT[position]);
            container.addView(textView);
            return textView;*/
            NewsPullToRefreshListView pullToRefreshListView = new NewsPullToRefreshListView(getContext());
            container.addView(pullToRefreshListView);
            return pullToRefreshListView;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView((View) object);
        }

        @Override //indicator 指示器会调用这个方法获得显示的标题
        public CharSequence getPageTitle(int position) {
            return CONTENT[position];
        }
    };

}
