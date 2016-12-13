package com.example.webviewhtml5;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.net.http.SslError;
import android.os.Bundle;
import android.view.KeyEvent;
import android.webkit.SslErrorHandler;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class MainActivity extends Activity {

	private WebView webView;
	private String url = "http://lbh.zhangwoo.cn/?m=home&c=index&a=home";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		initWebView();
	}

	@SuppressWarnings("deprecation")
	@SuppressLint("SetJavaScriptEnabled")
	private void initWebView() {
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
		//web.setBlockNetworkImage(true);// 把图片加载放在最后来加载渲染

		webView.loadUrl(url);
		webView.setWebViewClient(new WebViewClient() {
			@Override
			public boolean shouldOverrideUrlLoading(WebView view, String url) {
				// 重写此方法表明点击网页里面的链接还是在当前的webview里跳转，不跳到浏览器那边
				view.loadUrl(url);
				return true;

			}

			@Override
			public void onReceivedSslError(WebView view,
					SslErrorHandler handler, SslError error) {
				// 重写此方法可以让webview处理https请求
				handler.proceed();
			}
		});
	}

	@Override
	// 设置回退
	// 覆盖Activity类的onKeyDown(int keyCoder,KeyEvent event)方法
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		if ((keyCode == KeyEvent.KEYCODE_BACK) && webView.canGoBack()) {
			webView.goBack(); // goBack()表示返回WebView的上一页面
			return true;
		}
		return false;
	}

	/***
	 * 防止WebView加载内存泄漏
	 */
	@Override
	protected void onDestroy() {
		super.onDestroy();
		webView.removeAllViews();
		webView.destroy();
	}
}
