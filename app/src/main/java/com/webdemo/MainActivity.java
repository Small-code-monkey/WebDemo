package com.webdemo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;

import butterknife.Bind;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {

    @Bind(R.id.webview)
    WebView webview;
    @Bind(R.id.web_progressBar)
    ProgressBar webProgressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        webview.loadUrl("http://www.baidu.com");

        WebSettings webSettings = webview.getSettings();
        webSettings.setLoadWithOverviewMode(true);
        webSettings.setJavaScriptEnabled(true);
        webSettings.setAppCacheEnabled(true);
        webSettings.setLayoutAlgorithm(WebSettings.LayoutAlgorithm.SINGLE_COLUMN);
        webSettings.setSupportZoom(true);

        webview.setWebChromeClient(new WebViewLoadingStart());
        webview.setWebViewClient(new WebViewLoadingEnd());
    }

    /**
     * 加载时
     */
    private class WebViewLoadingStart extends WebChromeClient {
        @Override
        public void onProgressChanged(android.webkit.WebView view, int newProgress) {
            webProgressBar.setVisibility(View.VISIBLE);
            webProgressBar.setProgress(newProgress);
        }
    }

    /**
     * 加载结束
     */
    private class WebViewLoadingEnd extends WebViewClient {
        @Override
        public void onPageFinished(WebView view, String url) {
            webProgressBar.setVisibility(View.GONE);
        }
    }
}
