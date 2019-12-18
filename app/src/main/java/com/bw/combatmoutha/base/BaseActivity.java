package com.bw.combatmoutha.base;

import android.app.Activity;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

// TODO: 2019/12/17 必须继承  AppCompatActivity
public abstract class BaseActivity<P extends BasePresenter> extends AppCompatActivity {
    protected P mPresenter;

    //todo 这个方法 onCreate 方法容易选错
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(layoutId());
        mPresenter = providePresenter();
        // TODO: 2019/12/17 必须 mpresenter 不为 null 再挂载
        if (mPresenter != null) {
            mPresenter.attach(this);
        }
        initView();
        initData();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        // TODO: 2019/12/17
        if (mPresenter != null) {
            mPresenter.detach();
        }
    }

    protected abstract void initData();

    protected abstract void initView();

    protected abstract P providePresenter();


    protected abstract int layoutId();
}
