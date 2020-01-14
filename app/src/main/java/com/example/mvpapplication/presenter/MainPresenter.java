package com.example.mvpapplication.presenter;

import android.widget.Toast;

import com.example.mvpapplication.base.BasePresenter;
import com.example.mvpapplication.base.BasePresenterImpl;
import com.example.mvpapplication.base.BaseView;
import com.example.mvpapplication.base.MainContract;

import org.json.JSONObject;

import java.util.Date;

import javax.inject.Inject;

/**
 * @Author: david.lvfujiang
 * @Date: 2019/9/16
 * @Describe:
 */
public class MainPresenter extends BasePresenterImpl<MainContract.IMainView> implements MainContract.IMainPresenter {
     Date date;
    @Inject
    public MainPresenter(Date date) {
this.date =date;
    }

    public Date getDate() {
        return date;
    }

    @Override
    public void processMessage() {
        mView.succes("哈啊哈哈");
        detachView();
    }

}
