package com.bw.combatmoutha.utils;

import android.util.Log;

public class MyCaught implements Thread.UncaughtExceptionHandler {
    @Override
    public void uncaughtException(Thread t, Throwable e) {
        Log.e("TAG", "捕获到了异常" + e.getMessage());
    }
}
