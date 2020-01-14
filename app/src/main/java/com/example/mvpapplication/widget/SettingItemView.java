package com.example.mvpapplication.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.util.AttributeSet;
import android.util.Log;
import android.util.TypedValue;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.mvpapplication.R;
import com.tuyenmonkey.mkloader.MKLoader;


/**
 * 设置条目组合控件
 */

public class SettingItemView extends RelativeLayout {
    Context context;
    ImageView iconRight;
    TextView menuTextLeft;
    MKLoader loader;

    int textColor;
    float textSize;
    int rightIconId = R.mipmap.return_back_icon;
    String text = "";

    public void setTextColor(int textColor) {
        this.textColor = textColor;
    }

    public void setTextSize(float textSize) {
        this.textSize = textSize;
    }

    public void setRightIconId(int rightIconId) {
        this.rightIconId = rightIconId;
    }

    public void setText(String text) {
        this.text = text;
    }

    public SettingItemView(Context context) {
        super(context);
        this.context = context;
        initView();
        Log.e("s", "sss");
    }

    public SettingItemView(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.context = context;
        initView();
        initAttrs(context, attrs);
        initData();

    }

    public SettingItemView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

    }

    public void initView() {
        inflate(context, R.layout.layout_item_setting, this);
        iconRight = getView(R.id.icon_right);
        menuTextLeft = getView(R.id.menu_text_left);
    }

    public void initData() {
        if (textColor != 0) {
            menuTextLeft.setTextColor(textColor);
        }
        menuTextLeft.setTextSize(TypedValue.COMPLEX_UNIT_PX, textSize);
        iconRight.setImageResource(rightIconId);
        if (text != null) {
            menuTextLeft.setText(text);
        }
    }

    private void initAttrs(Context context, AttributeSet attrs) {
        //解析attr文件
        TypedArray ta = context.obtainStyledAttributes(attrs, R.styleable.MenuItemView);
        //获取文字颜色
        textColor = ta.getColor(R.styleable.MenuItemView_TextColor, Color.BLACK);
        textSize = ta.getDimension(R.styleable.MenuItemView_textSize, getResources().getDimension(R.dimen.demension_dp1));
        //获取右侧图标
        rightIconId = ta.getResourceId(R.styleable.MenuItemView_rightIcon, R.mipmap.return_back_icon);
        text = ta.getString(R.styleable.MenuItemView_text);
        ta.recycle();

    }

    public void showLoadding(Boolean b) {
        if (b == true) {
            loader.setVisibility(VISIBLE);
            iconRight.setVisibility(GONE);
        } else {
            loader.setVisibility(GONE);
            iconRight.setVisibility(VISIBLE);
        }
    }

    public CharSequence getMenuTextLeft() {
        return menuTextLeft.getText();
    }

    public void setMenuTextLeft(String text) {
        menuTextLeft.setText(text);
    }

    public <T extends View> T getView(int id) {
        return (T) findViewById(id);
    }
}
