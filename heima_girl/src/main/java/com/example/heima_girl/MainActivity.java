package com.example.heima_girl;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.gson.Gson;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import jp.wasabeef.glide.transformations.CropCircleTransformation;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";
    @BindView(R.id.listview)
    ListView mListview;
    private Gson mGson = new Gson();
    private boolean isLoading = false;
    private List<ResultBean.ResultsBean> mListData = new ArrayList<ResultBean.ResultsBean>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //绑定activity
        ButterKnife.bind(this);
        mListview.setAdapter(mAdapter);
        mListview.setOnScrollListener(new AbsListView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(AbsListView view, int scrollState) {
                if (scrollState == SCROLL_STATE_IDLE) {
                    if (mListview.getLastVisiblePosition() == mListData.size() - 1 && !isLoading) {
                        loadMoreData();
                    }
                }
            }


            public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {

            }
        });
        //sendSyncRequest();
        sendAsyncRequest();
    }

    private void loadMoreData() {
        //异步请求 ,在子线程执行
        isLoading = true;
        OkHttpClient okHttpClient = new OkHttpClient();
        int nextIndex = mListData.size() / 10 + 1;
        String url = "http://gank.io/api/data/福利/10/" + nextIndex;
        Request request = new Request.Builder().get().url(url).build();
        okHttpClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String result = response.body().string();
                ResultBean resultBean = mGson.fromJson(result, ResultBean.class);
                mListData.addAll(resultBean.getResults());
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        mAdapter.notifyDataSetChanged();
                    }
                });
                isLoading = false;
                //Log.d(TAG, "onResponse: " + resultBean.getResults().get(0).getUrl());

            }
        });
    }


    private void sendAsyncRequest() {
        //异步请求 ,在子线程执行
        OkHttpClient okHttpClient = new OkHttpClient();
        String url = "http://gank.io/api/data/%E7%A6%8F%E5%88%A9/10/1";
        Request request = new Request.Builder().get().url(url).build();
        okHttpClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String result = response.body().string();
                ResultBean resultBean = mGson.fromJson(result, ResultBean.class);
                mListData.addAll(resultBean.getResults());
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        mAdapter.notifyDataSetChanged();
                    }
                });
                //Log.d(TAG, "onResponse: " + resultBean.getResults().get(0).getUrl());

            }
        });

    }

    private void sendSyncRequest() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                OkHttpClient okHttpClient = new OkHttpClient();
                String url = "http://gank.io/api/data/%E7%A6%8F%E5%88%A9/10/1";
                Request request = new Request.Builder().get().url(url).build();
                try {
                    Response response = okHttpClient.newCall(request).execute();
                    Log.d(TAG, "response." + response.body().string());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    private BaseAdapter mAdapter = new BaseAdapter() {
        @Override
        public int getCount() {
            return mListData.size();
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
            ViewHolder holder = null;
            if (convertView == null) {
                convertView = View.inflate(MainActivity.this, R.layout.item_list, null);
                holder = new ViewHolder(convertView);
                convertView.setTag(holder);
            } else {
                holder = (ViewHolder) convertView.getTag();
            }
            ResultBean.ResultsBean resultsBean = mListData.get(position);
            holder.mTextView.setText(resultsBean.getPublishedAt());
            String url = resultsBean.getUrl();
            Glide.with(MainActivity.this).load(url).centerCrop().
                    bitmapTransform(new CropCircleTransformation(MainActivity.this)).
                    into(holder.mImageView);
            return convertView;
        }
    };

    static class ViewHolder {
        ImageView mImageView;
        TextView mTextView;

        public ViewHolder(View root) {
            mImageView = (ImageView) root.findViewById(R.id.iv);
            mTextView = (TextView) root.findViewById(R.id.tv);

        }

    }

}