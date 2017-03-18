package com.example.smartbj.ui.fragment;

import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.example.smartbj.R;

import butterknife.BindView;

/**
 * Created by HSAEE on 2017/3/17.
 */

public class MenuFragment extends BaseFragment {
    @BindView(R.id.listview)
    ListView mListview;
    private String[] mMenuTitles = {"新闻", "专题", "组图", "互动"};
    private int mLastPostion;

    @Override
    public int getResLayoutId() {
        return R.layout.fragment_menu;
    }

    @Override
    protected void init() {
        super.init();
        mListview.setAdapter(mAdapter);
        mListview.setOnItemClickListener(mListener);
    }

    private BaseAdapter mAdapter = new BaseAdapter() {
        @Override
        public int getCount() {
            return mMenuTitles.length;
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
                convertView = View.inflate(getContext(), R.layout.item_menu_list, null);
            }
            ((TextView) convertView).setText(mMenuTitles[position]);
            if (position == 0) {
                convertView.setEnabled(true);
            } else {
                convertView.setEnabled(false);
            }
            return convertView;
        }
    };

    private AdapterView.OnItemClickListener mListener = new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            //点击后处理item的颜色选择
            boolean isChanged = (position != mLastPostion);
            if (mMenuChangeListener != null) {
                mMenuChangeListener.onMenuItemClick(position, isChanged);
            }

            if (position == mLastPostion) {
                return;
            }

            parent.getChildAt(position).setEnabled(true);
            parent.getChildAt(mLastPostion).setEnabled(false);
            mLastPostion = position;
        }
    };

    public interface OnMenuChangeListener {
        void onMenuItemClick(int postion, boolean isChanged);
    }

    public void setOnMenuChangeListener(OnMenuChangeListener listener) {
        mMenuChangeListener = listener;
    }

    private OnMenuChangeListener mMenuChangeListener;
}
