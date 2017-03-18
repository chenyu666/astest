package com.example.smartbj.ui.fragment;

import android.util.SparseArray;
import android.widget.FrameLayout;
import android.widget.RadioGroup;

import com.example.smartbj.R;
import com.example.smartbj.ui.widget.GovAffairsTabPage;
import com.example.smartbj.ui.widget.HomeTabPage;
import com.example.smartbj.ui.widget.NewsCenterTabPager;
import com.example.smartbj.ui.widget.SettingTabPage;
import com.example.smartbj.ui.widget.SmartServiceTabPage;
import com.example.smartbj.ui.widget.TabPage;

import butterknife.BindView;

/**
 * Created by HSAEE on 2017/3/17.
 */

public class HomeFragment extends BaseFragment {
    @BindView(R.id.tab_page_container)
    FrameLayout mTabPageContainer;
    @BindView(R.id.tab_container)
    RadioGroup mRadioGroup;
    //tabpage的缓存   ,是一个整形数到对象的映射
    private SparseArray<TabPage> mSparseArray = new SparseArray<TabPage>();
    //记录当前的tabpage
    private TabPage mCurrentTabpage;

    @Override
    public int getResLayoutId() {
        return R.layout.fragment_home;
    }

    @Override
    protected void init() {
        super.init();


        mRadioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                TabPage tabPage = null;
                //查找内存缓存中有没有缓存TabPage, 如果有则使用缓存的TabPage，没有就创建一个
                if (mSparseArray.get(checkedId) != null) {
                    tabPage = mSparseArray.get(checkedId);
                    //Toast.makeText(getContext(), "从缓存中获取TabPage", Toast.LENGTH_SHORT).show();
                } else {
                    //Toast.makeText(getContext(), "新建TabPage", Toast.LENGTH_SHORT).show();
                   // tabPage = new TabPage(getContext());
                    //多态  , 创建对应的tabpage
                    tabPage = null;
                    switch (checkedId) {
                        case R.id.tab_home:
                            tabPage = new HomeTabPage(getContext());
                            tabPage.hideMenu();
                            tabPage.setTitle("首页");
                            break;
                        case R.id.tab_news_center:
                            tabPage = new NewsCenterTabPager(getContext());
                            tabPage.setTitle("新闻中心");
                            break;
                        case R.id.tab_smart_service:
                            tabPage = new SmartServiceTabPage(getContext());
                            tabPage.setTitle("智慧服务");
                            break;
                        case R.id.tab_gov_affairs:
                            tabPage = new GovAffairsTabPage(getContext());
                            tabPage.setTitle("政务");
                            break;
                        case R.id.tab_settings:
                            tabPage = new SettingTabPage(getContext());
                            tabPage.setTitle("设置中心");
                            tabPage.hideMenu();
                            break;
                    }
                    mCurrentTabpage = tabPage;
                    mSparseArray.put(checkedId, tabPage);
                    tabPage.setOnTabpageClickListener(new TabPage.OnTabpageClickListener() {
                        @Override
                        public void onTabpageClick() {
                            //回调主页处理tabpage的点击事件  传递给主页处理点击事件
                            if (mListener != null) {
                                mListener.onHomeTabpageClick();
                            }
                        }
                    });
                }



                mTabPageContainer.removeAllViews();
                mTabPageContainer.addView(tabPage);
                //通知外界发生了tab点击更换
                if (mListener != null) {
                    mListener.onTabSelected(checkedId);
                }
            }
        });
        //默认选中首页
        mRadioGroup.check(R.id.tab_home);
    }

    public void onMenuChanged(int postion) {
        //Toast.makeText(getContext(), "homefragment" + postion, Toast.LENGTH_SHORT).show();
        //menu 获得点击 ---honmeactivyty ----当前homefragment 将点击事件分发给framelayput 里面的tabpage处理
        //用变量记录每次创建的tabpage,然后事件传递回tagpage处理
        mCurrentTabpage.onMenuChanged( postion);
    }

    //点击vadio group 的回调
    public interface OnHomeChangeListener {
        void onTabSelected(int checkedId);

        void onHomeTabpageClick();
    }

    OnHomeChangeListener mListener;
    public void setOnTabSelected(OnHomeChangeListener listener) {
        mListener = listener;
    }


}
