package com.bw.combatmoutha;

import android.app.Application;

// TODO: 2019/12/17         App 必须清单文件注册,   不注册是灰色
public class App extends Application {

    public static App app;

    @Override
    public void onCreate() {
        super.onCreate();
        app=this;
    }
}
