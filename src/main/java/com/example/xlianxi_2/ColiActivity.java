package com.example.xlianxi_2;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.example.xlianxi_2.Adapter.ColiAdapter;
import com.example.xlianxi_2.Bean.Bean;
import com.example.xlianxi_2.Bean.DbBean;
import com.example.xlianxi_2.Db.Dbhelper;

import java.util.ArrayList;
import java.util.List;

/**
 * @author 李玉莲
 * @date 2019-5-30 21:06:43
 **/
public class ColiActivity extends AppCompatActivity {

    private RecyclerView rlva;
    private ArrayList<DbBean> list= new ArrayList<>();;
    private ColiAdapter coliAdapter;

    public ColiActivity() {
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_coli);
        initView();
        initData();

    }

    private void initData() {
        List<DbBean> dbBeans = Dbhelper.dbBeanDao.loadAll();
        list.addAll(dbBeans);
        coliAdapter.notifyDataSetChanged();
    }

    private void initView() {
        rlva = (RecyclerView) findViewById(R.id.rlva);
        rlva.setLayoutManager(new LinearLayoutManager(this));
        coliAdapter = new ColiAdapter(this, list);
        rlva.setAdapter(coliAdapter);
        coliAdapter.setOnclick(new ColiAdapter.Onclick() {
            @Override
            public void onclick(final int position) {
                new AlertDialog.Builder( ColiActivity.this).setTitle("跳转")
                        .setMessage("是否删除数据库").setPositiveButton("确认", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        DbBean dbBean = list.get(position);
                        list.remove(position);
                        Dbhelper.dbBeanDao.delete(dbBean);
                        coliAdapter.notifyDataSetChanged();
                        Toast.makeText(ColiActivity.this, "删除成功", Toast.LENGTH_SHORT).show();
                    }
                }).setNeutralButton("取消",null).show();
            }
        });
    }
}
