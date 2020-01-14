package com.example.mvpapplication.module;

import android.app.Activity;

import com.alibaba.fastjson.JSONObject;
import com.example.mvpapplication.base.BaseView;
import com.example.mvpapplication.base.MainContract;

import java.util.Date;

import dagger.Module;
import dagger.Provides;

/**
 * @Author: david.lvfujiang
 * @Date: 2019/10/25
 * @Describe:
 */
@Module
public class MainModule {
    Date dateTime;

    public MainModule(Date date) {
        this.dateTime = date;
    }

    @Provides
    public Date getDate() {
        return dateTime;
    }


}
