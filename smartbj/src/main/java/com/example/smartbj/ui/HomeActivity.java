package com.example.smartbj.ui;

import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;

import com.example.smartbj.R;
import com.example.smartbj.ui.fragment.HomeFragment;
import com.example.smartbj.ui.fragment.MenuFragment;
import com.jeremyfeinstein.slidingmenu.lib.SlidingMenu;
import com.jeremyfeinstein.slidingmenu.lib.app.SlidingFragmentActivity;

/**
 * Created by HSAEE on 2017/3/17.
 */
public class HomeActivity extends SlidingFragmentActivity {

    private MenuFragment mMenuFragment;
    private SlidingMenu mSlidingMenu;
    private HomeFragment mHomeFragment;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initLeftMenu();
        initSlidingMenu();
        initContent();
        //控制 点击事件的切换
        //点击拉出和拉回侧滑菜单,点击事件发生在自定义控件tabpage,先将事件回调给tabpage的容器homefragmeng
        //再把点击事件向上回调给能够控制拉回和拉出菜单的顶级容器homeactivity
        initEvent();
    }

    private void initEvent() {
        //根据  homefragment的vadio选中状态设置侧滑菜单能否拉出
        mHomeFragment.setOnTabSelected(new HomeFragment.OnHomeChangeListener() {
            @Override
            //根据  homefragment的vadio选中状态设置侧滑菜单能否拉出
            public void onTabSelected(int checkedId) {
                if (checkedId == R.id.tab_home || checkedId == R.id.tab_settings) {
                    mSlidingMenu.setTouchModeAbove(SlidingMenu.TOUCHMODE_NONE);  //设置侧滑不能拉出
                    //Toast.makeText(HomeActivity.this, "不能拉", Toast.LENGTH_SHORT).show();
                } else {
                    mSlidingMenu.setTouchModeAbove(SlidingMenu.TOUCHMODE_MARGIN); //恢复其他从边缘拉出
                }
            }

            @Override
            ////点击拉出和拉回侧滑菜单,点击事件发生在自定义控件tabpage,先将事件回调给tabpage的容器homefragmeng
            //再把点击事件向上回调给能够控制拉回和拉出菜单的顶级容器homeactivity
            public void onHomeTabpageClick() {
                mSlidingMenu.toggle();
            }
        });


        //设置侧滑菜单的监听
        mMenuFragment.setOnMenuChangeListener(new MenuFragment.OnMenuChangeListener() {
            @Override
            public void onMenuItemClick(int postion,boolean isChanged) {
                //接收到menufragment的事件后将事件分发给hemofragment处理
                mSlidingMenu.toggle();
                if (isChanged) {
                    mHomeFragment.onMenuChanged(postion);
                }

            }
        });

    }

    /**
     * 设置内容区域布局
     */

    private void initContent() {
        setContentView(R.layout.content_frame);
        mHomeFragment = new HomeFragment();
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.content_frame, mHomeFragment)
                .commit();
    }

    /**
     *   侧滑菜单的配置
     */
    private void initSlidingMenu() {
        // customize the SlidingMenu
        mSlidingMenu = getSlidingMenu();
        mSlidingMenu.setBehindOffsetRes(R.dimen.slidingmenu_offset);//侧滑菜单偏移量
        mSlidingMenu.setTouchModeAbove(SlidingMenu.TOUCHMODE_MARGIN);//设置从边缘拉出侧滑菜单
        getSlidingMenu().setMode(SlidingMenu.LEFT);//设置左边可拉出侧滑菜单
    }

    /**
     * 初始化左边菜单的布局
     */
    private void initLeftMenu() {
        setBehindContentView(R.layout.menu_frame);//设置左边侧滑菜单的布局
        FragmentTransaction t = this.getSupportFragmentManager().beginTransaction();
        mMenuFragment = new MenuFragment();
        t.replace(R.id.menu_frame, mMenuFragment);
        t.commit();
    }

    /**
     *
     */
}
