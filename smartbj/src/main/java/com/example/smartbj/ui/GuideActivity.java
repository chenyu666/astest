package com.example.smartbj.ui;

import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;

import com.example.smartbj.R;
import com.example.smartbj.utils.SpUtils;
import com.viewpagerindicator.CirclePageIndicator;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.example.smartbj.R.id.indicator;

/**
 * Created by HSAEE on 2017/3/17.
 */
public class GuideActivity extends BaseActivity {
    @BindView(R.id.btn_go)
    Button mBtnGo;
    @BindView(indicator)
    CirclePageIndicator mIndicator;
    private int[] mImages = {R.mipmap.guide_1, R.mipmap.guide_2, R.mipmap.guide_3};
    @BindView(R.id.viewpager)
    ViewPager mViewpager;


    @Override
    public int getLayoutResId() {
        return R.layout.activity_guide1;
    }

    @Override
    protected void init() {
        super.init();
        //设置适配器
        mViewpager.setAdapter(mAdapter);
        //设置监听
        mViewpager.addOnPageChangeListener(mListener);

        mIndicator.setViewPager(mViewpager);
    }

    private PagerAdapter mAdapter = new PagerAdapter() {
        @Override
        public int getCount() {
            return mImages.length;
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == object;
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            ImageView imageView = new ImageView(GuideActivity.this);
            imageView.setImageResource(mImages[position]);
            imageView.setScaleType(ImageView.ScaleType.FIT_XY);  //设置图片拉伸,铺满整个imageview
            container.addView(imageView);
            return imageView;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {

            container.removeView((View) object);
        }
    };

    private ViewPager.OnPageChangeListener mListener = new ViewPager.OnPageChangeListener() {
        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

        }

        @Override
        public void onPageSelected(int position) {
            if (position == mImages.length - 1) {
                mBtnGo.setVisibility(View.VISIBLE);
                mBtnGo.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                            /*Intent intent = new Intent(GuideActivity.this,HomeActivity.class);
                            startActivity(intent);*/
                        SpUtils.saveBoolean(GuideActivity.this, SpUtils.KEY_ISSTARTED, true);
                        navigateTo(HomeActivity.class);

                    }
                });
            } else {
                mBtnGo.setVisibility(View.GONE);
            }
        }

        @Override
        public void onPageScrollStateChanged(int state) {

        }
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }
}
