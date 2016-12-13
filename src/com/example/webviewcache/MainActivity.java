package com.example.webviewcache;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.webkit.WebSettings;
import android.webkit.WebSettings.RenderPriority;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;

public class MainActivity extends Activity {

	private WebView webView;
	private String url = "https://wap.baidu.com/";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		initView();
	}

	public void initView() {
		webView = (WebView) findViewById(R.id.activity_webview);
		webView.requestFocus();
		webView.setHorizontalScrollBarEnabled(false);
		webView.setVerticalScrollBarEnabled(false);
		initWebView();

	}

	@SuppressWarnings("deprecation")
	@SuppressLint("SetJavaScriptEnabled")
	private void initWebView() {

		webView.getSettings().setJavaScriptEnabled(true);
		// 设置 缓存模式
		if (NetUtils.isNetworkAvailable(MainActivity.this)) {
			webView.getSettings().setCacheMode(WebSettings.LOAD_DEFAULT);
		} else {
			webView.getSettings().setCacheMode(
					WebSettings.LOAD_CACHE_ELSE_NETWORK);
		}
		// webView.getSettings().setBlockNetworkImage(true);// 把图片加载放在最后来加载渲染
		webView.getSettings().setRenderPriority(RenderPriority.HIGH);
		// 支持多窗口
		webView.getSettings().setSupportMultipleWindows(true);
		// 开启 DOM storage API 功能
		webView.getSettings().setDomStorageEnabled(true);
		// 开启 Application Caches 功能
		webView.getSettings().setAppCacheEnabled(true);
		onLoad();
	}

	@SuppressWarnings("deprecation")
	@SuppressLint("SetJavaScriptEnabled")
	public void onLoad() {

		try {
			webView.setWebViewClient(new WebViewClient() {

				@Override
				public void onLoadResource(WebView view, String url) {

					Log.i("tag", "onLoadResource url=" + url); // 开始加载
					super.onLoadResource(view, url);
				}

				@Override
				public boolean shouldOverrideUrlLoading(WebView webview,
						String url) {

					Log.i("tag", "intercept url=" + url);
					// 重写此方法表明点击网页里面的链接还是在当前的webview里跳转，不跳到浏览器那边
					webview.loadUrl(url);

					return true;
				}

				@Override
				public void onPageFinished(WebView view, String url) {

					String title = view.getTitle(); // 得到网页标题

					Log.e("tag", "onPageFinished WebView title=" + title);

				}

				@Override
				public void onReceivedError(WebView view, int errorCode,
						String description, String failingUrl) {

					Toast.makeText(getApplicationContext(), "加载错误",
							Toast.LENGTH_LONG).show();
				}
			});
			webView.loadUrl(url);
		} catch (Exception e) {
			return;
		}
	}

	@Override
	// 设置回退
	// 覆盖Activity类的onKeyDown(int keyCoder,KeyEvent event)方法
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		if ((keyCode == KeyEvent.KEYCODE_BACK) && webView.canGoBack()) {
			webView.goBack(); // goBack()表示返回WebView的上一页面
			return true;
		} else {
			finish();
			return true;
		}
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
