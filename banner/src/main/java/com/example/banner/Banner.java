package com.example.banner;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

/**
 * Created by HSAEE on 2017/3/16.
 */

public class Banner extends RelativeLayout {
    private Context mContext;

    private int[] mImages = {R.mipmap.icon_1, R.mipmap.icon_2, R.mipmap.icon_3, R.mipmap.icon_4, R.mipmap.icon_5};
    private String[] mTitles = {"Fight For Dream", "I believe I'm black horse", "HeiMa Open Class", "Google IO", "Easy 10000+"};
    private TextView mTvTitle;
    private ViewPager mViewPager;
    private LinearLayout mLinearLayout;
    private int mLastPostion = 0;
    private int mDotSize;

    public Banner(Context context) {
        this(context, null);
    }

    public Banner(Context context, AttributeSet attrs) {
        super(context, attrs);
        //获取自定义的属性,attrs配置的所有属性
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.BannerStyle);
        //拿到点的大小属性 5dp-->转换对应的像素
        mDotSize = typedArray.getDimensionPixelSize(R.styleable.BannerStyle_dot_size, 10);

        typedArray.recycle();
        View.inflate(context, R.layout.banner_layout, this);
        mContext = context;
        init();
    }

    private void init() {


        mTvTitle = (TextView) findViewById(R.id.tv_title);
        mTvTitle.setText(mTitles[0]);
        //动态初始化指示器
        mLinearLayout = (LinearLayout) findViewById(R.id.dots_container);
        initDots();


        mViewPager = (ViewPager) findViewById(R.id.view_pager);
        mViewPager.setAdapter(mAdapter);
        mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override//页面发生滚动的回调
            /**
             *  当页面发生滚动的回调
             * @param position 滚动页面的下标
             * @param positionOffset 页面滚动偏移量的像素个数 / viewpager的宽度
             * @param positionOffsetPixels 页面滚动偏移量的像素个数
             */
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override//页面被选中的回调
            public void onPageSelected(int position) {
                //选中后切换标题 ,点的背景  ,上一个点变白,更新上次选中的位置
                position = position % mImages.length;
                if (mLastPostion == position) {
                    return;
                }


                mTvTitle.setText(mTitles[position]);
                View dot = mLinearLayout.getChildAt(position);
                dot.setBackgroundResource(R.drawable.dot_select);
                //将上一个选中的点变白
                View preView = mLinearLayout.getChildAt(mLastPostion);
                preView.setBackgroundResource(R.drawable.dot_normal);
                mLastPostion = position;

            }

            @Override //页面滚动状态改变的回调
            public void onPageScrollStateChanged(int state) {
                //空闲状态,拖动状态,和拖动后到viewpager停稳的状态
            }
        });

        int initPostion = Integer.MAX_VALUE / 2;
        initPostion = initPostion - 3;
        mViewPager.setCurrentItem(initPostion);
        //开启自动轮播
        //startLoop();
    }

    private void startLoop() {
        postDelayed(mTicker, 1500);
    }

    private Runnable mTicker = new Runnable() {
        @Override
        public void run() {
            //viewpager切换到下一页
            mViewPager.setCurrentItem(mViewPager.getCurrentItem() + 1);
            startLoop();
        }
    };

    @Override   //离开页面的回调
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        removeCallbacks(mTicker);

    }

    @Override  //添加到页面的回调
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        startLoop();
    }

    private void initDots() {
        for (int i = 0; i < mTitles.length; i++) {
            View dot = new View(mContext);
            //布局点
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(mDotSize, mDotSize);
            params.rightMargin = 8;

            dot.setLayoutParams(params);
            if (i == 0) {
                dot.setBackgroundResource(R.drawable.dot_select);
            } else {
                dot.setBackgroundResource(R.drawable.dot_normal);
            }
            mLinearLayout.addView(dot);
        }
    }

    private PagerAdapter mAdapter = new PagerAdapter() {
        @Override
        public int getCount() {
            //return mTitles.length;
            //设置无限轮播
            return Integer.MAX_VALUE;
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            //判断添加的子控件是否标记过
            return view == object;
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            position = position % mImages.length;
            ImageView imageView = new ImageView(mContext);
            imageView.setImageResource(mImages[position]);
            container.addView(imageView);
            return imageView;  //返回子控件本身标记是否合法设置在适配器的子控件
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            //super.destroyItem(container, position, object);
            container.removeView((View) object);
        }
    };


}
