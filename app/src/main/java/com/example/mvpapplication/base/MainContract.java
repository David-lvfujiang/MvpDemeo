package com.example.mvpapplication.base;

import android.view.View;

/**
 * @Author: david.lvfujiang
 * @Date: 2019/9/16
 * @Describe:
 */
public interface MainContract {

        interface IMainView extends BaseView{
            void sendMessage();
            void succes(String content);
        }
        interface IMainPresenter
        {
            void processMessage();
        }


}
