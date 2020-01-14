package com.example.mvpapplication.base;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Context;
import android.content.pm.ActivityInfo;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.mvpapplication.R;
import com.weavey.loading.lib.LoadingLayout;

import javax.inject.Inject;

/**
 * @Author: david.lvfujiang
 * @Date: 2019/9/16
 * @Describe:
 */
public abstract class BaseActivity<T extends BasePresenter> extends AppCompatActivity implements BaseView {
    @Inject
    protected T presenter;
    protected Context mContext;
    private RelativeLayout mErrorLayout;
    private RelativeLayout mTitleLayout;
    private RelativeLayout mConectionLayout;
    private FrameLayout mContentContainer;
    private ImageView mReturnBack;
    private TextView mTitleText;
    private  LoadingLayout loadingLayout;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_base);
        init();
        setTranslucentStatus(this);
        initView();
        initData();
    }

    public void init() {
        mContext = getContext();

        mTitleLayout = findViewById(R.id.titleLayout);
        mReturnBack = mTitleLayout.findViewById(R.id.return_back_img);
        mTitleText = mTitleLayout.findViewById(R.id.return_back_title);
        mReturnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        mContext = this;
        mContentContainer = findViewById(R.id.contentContainer);
        // 加载内容布局
        View.inflate(mContext, getActivityLayoutID(), mContentContainer);
        // 强制竖屏
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
    }

    public void setTitle(String title) {
        mTitleText.setText(title);
    }

    public void hideTitle(String title) {
        mTitleLayout.setVisibility(View.GONE);
        mContentContainer.setVisibility(View.VISIBLE);
        mErrorLayout.setVisibility(View.GONE);
        mConectionLayout.setVisibility(View.GONE);

    }
    // 显示内容
    public void showContent() {
        mContentContainer.setVisibility(View.VISIBLE);
        mErrorLayout.setVisibility(View.GONE);
        mConectionLayout.setVisibility(View.GONE);

    }

    // 显示正在连接
    public void showConection() {
        loadingLayout = findViewById(R.id.loading_layout);

        loadingLayout.setVisibility(View.VISIBLE);
        loadingLayout.setStatus(LoadingLayout.Loading);
    }
    // 显示服务器异常
    public void showError() {
        mContentContainer.setVisibility(View.GONE);
        mErrorLayout.setVisibility(View.VISIBLE);
        mConectionLayout.setVisibility(View.GONE);
    }


    /**
     * 获取全局上下文
     * 这样子所有的子类直接使用mContext
     *
     * @return
     */
    @Override
    public Context getContext() {
        return this;
    }

    /**
     * 提供接口给子类实现
     *
     * @return
     */
    public abstract void setPresenter();

    /**
     * 初始化View
     */
    protected abstract void initView();

    protected abstract void initData();

    protected abstract int getActivityLayoutID();
    /**
     * 设置状态栏透明
     */
    @TargetApi(19)
    public static void setTranslucentStatus(Activity activity) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            //5.x开始需要把颜色设置透明，否则导航栏会呈现系统默认的浅灰色
            Window window = activity.getWindow();
            View decorView = window.getDecorView();
            //两个 flag 要结合使用，表示让应用的主体内容占用系统状态栏的空间
            int option = View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN | View.SYSTEM_UI_FLAG_LAYOUT_STABLE;
            decorView.setSystemUiVisibility(option);
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(Color.TRANSPARENT);
            //导航栏颜色也可以正常设置
            //window.setNavigationBarColor(Color.TRANSPARENT);
        } else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            Window window = activity.getWindow();
            WindowManager.LayoutParams attributes = window.getAttributes();
            int flagTranslucentStatus = WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS;
            attributes.flags |= flagTranslucentStatus;
            //int flagTranslucentNavigation = WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION; //attributes.flags |= flagTranslucentNavigation;
            window.setAttributes(attributes);
        }
    }
}
