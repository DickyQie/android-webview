package com.example.webviewhtmlcookiedemo;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.webkit.CookieManager;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

/****
 * 
 * @author zq
 *
 */
public class MainActivity extends Activity {

	public String urlSign = "http://xwwscs.com/app.php?platform=android&appkey=40a3e8e50fa27b8e6f1dd1a4b7454a0a&version=1.0&c=member&a=viewlogin";
	/***
	 * 测试账号  name:DickyQie  pwd:123456
	 */
	private WebView webView;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		initView();
		findViewById(R.id.button1).setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				startActivity(new Intent(MainActivity.this, Page.class));
			}
		});
	}

	@SuppressLint({ "SetJavaScriptEnabled", "JavascriptInterface" })
	private void initView() {
		webView = (WebView) findViewById(R.id.activity_webview);
		webView.requestFocus();
		webView.setHorizontalScrollBarEnabled(false);
		webView.setVerticalScrollBarEnabled(false);
		WebSettings web = webView.getSettings();
		web.setJavaScriptEnabled(true);
		web.setBuiltInZoomControls(true);
		web.setSupportZoom(true);
		web.setLayoutAlgorithm(WebSettings.LayoutAlgorithm.NARROW_COLUMNS);
		web.setUseWideViewPort(true);
		web.setLoadWithOverviewMode(true);
		web.setSavePassword(true);
		web.setSaveFormData(true);
		webView.loadUrl(urlSign);
		webView.setWebViewClient(new MyWebViewClient());
	}

	private class MyWebViewClient extends WebViewClient {
		public boolean shouldOverrideUrlLoading(WebView view, String url) {
			return true;
		}
		public void onPageFinished(WebView view, String url) {
			CookieManager cookieManager = CookieManager.getInstance();
			String CookieStr = cookieManager.getCookie(url);
			if (CookieStr != null) {
				Log.i("cookie", CookieStr);
				try {
					Log.i("转码后",  URLDecoder.decode(CookieStr, "utf-8"));
				} catch (UnsupportedEncodingException e) {
					e.printStackTrace();
				}
			}
			super.onPageFinished(view, url);
		}

	}

}
