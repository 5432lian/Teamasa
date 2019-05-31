package com.example.xlianxi_2;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.Toast;

import com.example.xlianxi_2.Adapter.HomeAdapter;
import com.example.xlianxi_2.Bean.Bean;
import com.example.xlianxi_2.Bean.DbBean;
import com.example.xlianxi_2.Db.Dbhelper;
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
 * @date 2019-5-30 20:59:34
 **/
public class DbActivity extends AppCompatActivity {

    private RecyclerView rlvw;
    private ArrayList<Bean.ResultBean.DataBean> list=new ArrayList<>();;
    private HomeAdapter homeAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_db);
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
                        homeAdapter.notifyDataSetChanged();
                    }
                });
            }
        });
    }

    private void initView() {
        rlvw = (RecyclerView) findViewById(R.id.rlvw);
        rlvw.setLayoutManager(new LinearLayoutManager(this));
        homeAdapter = new HomeAdapter(this, list);
        rlvw.setAdapter(homeAdapter);
        homeAdapter.setOnclick(new HomeAdapter.Onclick() {
            @Override
            public void onclick(final int position) {
              new AlertDialog.Builder(DbActivity.this).setTitle("跳转")
                      .setMessage("是确认插入数据库").setPositiveButton("确认", new DialogInterface.OnClickListener() {
                  @Override
                  public void onClick(DialogInterface dialog, int which) {
                      Bean.ResultBean.DataBean dataBean = list.get(position);
                      Dbhelper.dbBeanDao.insertOrReplaceInTx(new DbBean(null,dataBean.getUrl1(),dataBean.getContent()));
                      Toast.makeText(DbActivity.this, "收藏成功", Toast.LENGTH_SHORT).show();
                      startActivity(new Intent(DbActivity.this,ColiActivity.class));
                  }
              }).setNeutralButton("取消",null).show();
            }
        });
    }
}
