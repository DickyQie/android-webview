package com.example.webviewhttpcookiedemo;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.os.Bundle;
import android.webkit.CookieManager;
import android.webkit.CookieSyncManager;
import android.webkit.WebSettings;
import android.webkit.WebView;


public class PageMy extends Activity {

	public String urlSign = "http://xwwscs.com/app.php?platform=android&appkey=40a3e8e50fa27b8e6f1dd1a4b7454a0a&version=1.0&c=mcenter&a=index";
	WebView webView;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.mian);
		initView();
	}

	@SuppressLint("SetJavaScriptEnabled")
	private void initView() {
		// TODO Auto-generated method stub
		webView = (WebView) findViewById(R.id.activity_webview);
		webView.requestFocus();
		webView.setHorizontalScrollBarEnabled(false);
		webView.setVerticalScrollBarEnabled(false);
		
		 WebSettings web = webView.getSettings();
		 web.setJavaScriptEnabled(true); web.setBuiltInZoomControls(true);
		 web.setSupportZoom(true);
		 web.setLayoutAlgorithm(WebSettings.LayoutAlgorithm.NARROW_COLUMNS);
		 web.setUseWideViewPort(true); web.setLoadWithOverviewMode(true);
		 web.setSavePassword(true); web.setSaveFormData(true);
		 syncCookie(urlSign);
		 webView.loadUrl(urlSign);

	}

	/**
	 * 将cookie同步到WebView
	 * 
	 * @param url
	 *            WebView要加载的url
	 * @param cookie
	 *            要同步的cookie
	 */
	private void syncCookie(String url) {
		try {
			CookieSyncManager.createInstance(webView.getContext());
			CookieManager cookieManager = CookieManager.getInstance();
			cookieManager.setCookie(url,
					CookieUtil.getParam(getApplicationContext()).toString());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}


	
	
	
}
