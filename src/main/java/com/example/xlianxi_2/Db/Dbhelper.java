package com.example.xlianxi_2.Db;

import android.app.Application;

import com.example.xlianxi_2.dao.DaoMaster;
import com.example.xlianxi_2.dao.DbBeanDao;

public class Dbhelper extends Application {

    public static DbBeanDao dbBeanDao;

    @Override
    public void onCreate() {
        super.onCreate();
        initData();
    }

    private void initData() {
        DaoMaster.DevOpenHelper devOpenHelper = new DaoMaster.DevOpenHelper(this, "d.db");
        DaoMaster daoMaster = new DaoMaster(devOpenHelper.getWritableDatabase());
        dbBeanDao = daoMaster.newSession().getDbBeanDao();
    }

    public DbBeanDao getDbBeanDao() {
        return dbBeanDao;
    }
}
