package com.bw.combatmoutha.view.activity;

import android.util.Log;
import android.view.View;
import android.webkit.JavascriptInterface;
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
        webView.loadUrl("file:///android_asset/hello.html");
    }

    @Override
    protected void initView() {
        webView = findViewById(R.id.web);
        //解决应用内加载
        webView.setWebViewClient(new WebViewClient());
        //支持android调用js
        webView.getSettings().setJavaScriptEnabled(true);

        //支持js调用andorid
        webView.addJavascriptInterface(new JsToAndroid(), "android");


        button = findViewById(R.id.btn);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //1、android调用js的方法   tomsg()
//                webView.loadUrl("javascript:tomsg()");

                //2、android调用你js的方法   age(字符串)
                // TODO: 2019/12/18  注意要传递的参数是带着 单引号
                webView.loadUrl("javascript:age('我是android世界来的参数')");
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


    //这是js调用android的类  他有别名
    // TODO: 2019/12/18  方法上面必须有注解     @JavascriptInterface
    public class JsToAndroid {
        @JavascriptInterface
        public void getJsData() {
            Log.e("TAG", "js调用了android的 getJsData方法");
            Toast.makeText(SecondActivity.this, "js调用了android的 getJsData方法", Toast.LENGTH_SHORT).show();

        }

        @JavascriptInterface
        public void getJsData(String data) {
            Log.e("TAG", "js调用了android的 getJsData方法" + data);
            Toast.makeText(SecondActivity.this, "js调用了android的 getJsData方法" + data, Toast.LENGTH_SHORT).show();
        }
    }
}
