package com.example.mvpapplication.view;

import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.mvpapplication.Component.DaggerMianComponent;
import com.example.mvpapplication.R;
import com.example.mvpapplication.base.BaseActivity;
import com.example.mvpapplication.base.MainContract;
import com.example.mvpapplication.module.MainModule;
import com.example.mvpapplication.presenter.MainPresenter;
import com.example.mvpapplication.widget.SettingItemView;

import java.util.Date;

public class MainActivity extends BaseActivity<MainPresenter> implements MainContract.IMainView {
    @Override
    public void setPresenter() {
        DaggerMianComponent.builder().mainModule(new MainModule(new Date(System.currentTimeMillis()))).build().inject(this);
    }

    SettingItemView itemView;
    LinearLayout mMainLayout;

    /**
     * 初始化view
     */
    @Override
    protected void initView() {
        itemView = findViewById(R.id.menu);
        mMainLayout = findViewById(R.id.main_layout);
    }

    /**
     * 初始化数据
     */
    @Override
    protected void initData() {
        setPresenter();
        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                itemView.showLoadding(true);
            }
        });
        setTitle("你的城市");

         showConection();
        sendMessage();
        SettingItemView settingItemView = new SettingItemView(this);
        settingItemView.setText("你好吗");
        settingItemView.setTextSize(54);
        settingItemView.initData();
        mMainLayout.addView(settingItemView);
    }

    @Override
    protected int getActivityLayoutID() {
        return R.layout.activity_main;
    }

    /**
     * 执行业务
     */
    @Override
    public void sendMessage() {
        presenter.attachView(this);
        presenter.processMessage();
    }

    /**
     * 成功回调
     *
     * @param content
     */
    @Override
    public void succes(String content) {
        Toast.makeText(mContext, content, Toast.LENGTH_SHORT).show();
        Log.e("TAG", presenter.getDate().getTime()+"");
    }


}
