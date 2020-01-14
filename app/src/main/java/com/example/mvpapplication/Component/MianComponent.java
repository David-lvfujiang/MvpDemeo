package com.example.mvpapplication.Component;

import com.example.mvpapplication.module.MainModule;
import com.example.mvpapplication.view.MainActivity;

import dagger.Component;

/**
 * @Author: david.lvfujiang
 * @Date: 2019/10/25
 * @Describe:
 */
@Component(modules = MainModule.class)
public interface MianComponent {
    void inject(MainActivity activity);
}
