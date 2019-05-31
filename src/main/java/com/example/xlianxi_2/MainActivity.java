package com.example.xlianxi_2;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.example.xlianxi_2.Adapter.HomeAdapter;
import com.example.xlianxi_2.Bean.Bean;
import com.google.gson.Gson;

import java.io.IOException;
import java.util.ArrayList;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * @author 李玉莲
 * @date 2019-5-30 20:15:59
 **/
public class MainActivity extends AppCompatActivity {

    private RecyclerView rlv;
    private ArrayList<Bean.ResultBean.DataBean> list;
    private HomeAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        initData();
    }

    private void initData() {
        OkHttpClient okHttpClient = new OkHttpClient.Builder().build();
        Request request = new Request.Builder()
                .url("http://172.16.56.109:8080/a/d.txt")
                .build();
        Call call = okHttpClient.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Log.i("123","=====>"+e.getMessage());
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String string = response.body().string();
                final Bean bean = new Gson().fromJson(string, Bean.class);
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        list.addAll(bean.getResult().getData());
                        adapter.notifyDataSetChanged();
                    }
                });
            }
        });

    }

    private void initView() {
        rlv = (RecyclerView) findViewById(R.id.rlv);
        rlv.setLayoutManager(new LinearLayoutManager(this));
        list = new ArrayList<>();
        adapter = new HomeAdapter(this, list);
        rlv.setAdapter(adapter);
        adapter.setOnclick(new HomeAdapter.Onclick() {
            @Override
            public void onclick(int position) {
                startActivity(new Intent(MainActivity.this,DbActivity.class));
                finish();
            }
        });
    }
}
