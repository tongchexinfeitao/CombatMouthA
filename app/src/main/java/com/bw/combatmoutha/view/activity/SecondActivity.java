package com.bw.combatmoutha.view.activity;

import android.util.Log;
import android.view.View;
import android.webkit.JavascriptInterface;
import android.webkit.JsResult;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.Toast;

import com.bw.combatmoutha.R;
import com.bw.combatmoutha.base.BaseActivity;
import com.bw.combatmoutha.base.BasePresenter;

public class SecondActivity extends BaseActivity {

    private WebView webView;
    private Button button;

    @Override
    protected void initData() {
        //加载网页
        webView.loadUrl("file:///android_asset/Baibai.html");
    }

    @Override
    protected void initView() {
        webView = findViewById(R.id.web);
        //支持android调用js
        webView.getSettings().setJavaScriptEnabled(true);

        // TODO: 2019/12/19 要检查一下方法是否加了注解
        //支持js调用android
        webView.addJavascriptInterface(new JsToAndroid(), "android");

        //网页应用内加载
        webView.setWebViewClient(new WebViewClient());
        //允许js弹窗
        webView.setWebChromeClient(new WebChromeClient());


        button = findViewById(R.id.btn);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //调用有参的js方法
                webView.loadUrl("javascript:jsFunction2('我是android过来的')");
            }
        });

    }

    @Override
    protected BasePresenter providePresenter() {
        return null;
    }

    @Override
    protected int layoutId() {
        return R.layout.activity_second2;
    }


    /**
     * 1、方法名字必须固定
     * 2、方法必须加注解
     */
    public class JsToAndroid {
        @JavascriptInterface
        public void getAndroidXxxData(String data) {
            Log.e("TAG", "JsToAndroid getAndroidXxxData " + data);
            Toast.makeText(SecondActivity.this, "JsToAndroid getAndroidXxxData" + data, Toast.LENGTH_SHORT).show();
        }

    }
}
