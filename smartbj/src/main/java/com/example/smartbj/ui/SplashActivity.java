package com.example.smartbj.ui;

import android.content.Context;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.RotateAnimation;
import android.view.animation.ScaleAnimation;
import android.widget.ImageView;

import com.example.smartbj.R;
import com.example.smartbj.utils.SpUtils;

import butterknife.BindView;

/**
 * Created by HSAEE on 2017/3/16.
 */

public class SplashActivity extends BaseActivity {
    private static final int ANIMATION_DURATION = 1000;
    @BindView(R.id.iv_splash)
    ImageView mIvSplash;
    private Context mContext;

    @Override
    public int getLayoutResId() {
        return R.layout.activity_splash;
    }

    @Override
    protected void init() {
        super.init();
        mContext = this;
        //开启动画
        startAnimation();
    }

    private void startAnimation() {
        AnimationSet set = new AnimationSet(false);
        //旋转
        RotateAnimation rotateAnimation = new RotateAnimation(0,360,
                Animation.RELATIVE_TO_SELF,0.5f,
                Animation.RELATIVE_TO_SELF,0.5f);
        rotateAnimation.setDuration(ANIMATION_DURATION);
        set.addAnimation(rotateAnimation);
        //透明
        AlphaAnimation alphaAnimation = new AlphaAnimation(0,1);
        alphaAnimation.setDuration(ANIMATION_DURATION);
        set.addAnimation(alphaAnimation);
        //缩放
        ScaleAnimation scaleAnimation = new ScaleAnimation(0, 1, 0, 1,
                Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
        scaleAnimation.setDuration(ANIMATION_DURATION);

        set.addAnimation(scaleAnimation);
        mIvSplash.startAnimation(set);
        //设置监听  动画结束跳转到向导页面
       set.setAnimationListener(mListener);

    }


    private Animation.AnimationListener mListener = new Animation.AnimationListener() {
        @Override
        public void onAnimationStart(Animation animation) {

        }

        @Override
        public void onAnimationEnd(Animation animation) {
            /*Intent intent = new Intent(mContext,GuideActivity.class);
            startActivity(intent);
            finish();*/
            //
            boolean isStarted = SpUtils.getBoolean(mContext, SpUtils.KEY_ISSTARTED);
            if (isStarted) {
                navigateTo(HomeActivity.class);
            } else {
                navigateTo(GuideActivity.class);
            }

        }

        @Override
        public void onAnimationRepeat(Animation animation) {

        }
    };
}
