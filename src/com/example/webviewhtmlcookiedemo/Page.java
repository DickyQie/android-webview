package com.example.webviewhtmlcookiedemo;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.webkit.CookieManager;
import android.webkit.CookieSyncManager;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.Button;

public class Page extends Activity{
	
	
	private Button btn;
	public String urlSign = "http://xwwscs.com/app.php?platform=android&appkey=40a3e8e50fa27b8e6f1dd1a4b7454a0a&version=1.0&c=mcenter&a=index";
	private WebView webView;
	private CookieManager cookieManager;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		initView();
		
       findViewById(R.id.button1).setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
			       cookieManager = CookieManager.getInstance();  
			       cookieManager.setAcceptCookie(true);  
			       cookieManager.removeSessionCookie();
			       cookieManager.removeAllCookie();  

			}
		});
	}
	@SuppressLint("SetJavaScriptEnabled") private void initView() {
		// TODO Auto-generated method stub
		btn = (Button) findViewById(R.id.button1);
		btn.setText("清除缓存");
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
		
	}
	
	/**
	 * 将cookie同步到WebView
	 * @param url WebView要加载的url
	 * @param cookie 要同步的cookie
	 */
	public void syncCookie(String url,String cookie) {
	    CookieSyncManager.createInstance(webView.getContext());
	    CookieManager cookieManager = CookieManager.getInstance();
	    cookieManager.setCookie(url, cookie);
	}

}
