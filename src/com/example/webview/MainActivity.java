package com.example.webview;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.os.Bundle;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;

/****
 * 
 * WebView 实现JS效果和a标签的点击事件
 * 
 * @author zq
 *
 */
public class MainActivity extends Activity {

	public String URL = "http://bajie.zhangwoo.cn/app.php?platform=android&appkey=5a379b5eed8aaae531df5f60b12100cfb6dff2c1&c=travel&a=home";
	WebView webView;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		webView = (WebView) findViewById(R.id.webview);
		webView.loadUrl(URL);
		initView();

	}
	@SuppressLint("SetJavaScriptEnabled") private void initView() {
		// TODO Auto-generated method stub
		webView.requestFocus();
		webView.setHorizontalScrollBarEnabled(true);
		webView.setVerticalScrollBarEnabled(true);
		WebSettings web = webView.getSettings();
		web.setJavaScriptEnabled(true);// 启用支持javascript
		web.setBuiltInZoomControls(true);
		web.setSupportZoom(true); // 是否支持屏幕双击缩放，但是下边的是前提
		web.setDefaultTextEncodingName("utf-8");// 设置编码格式
		// 覆盖WebView默认使用第三方或系统默认浏览器打开网页的行为，使网页用WebView打开
		webView.setWebViewClient(new WebViewClient() {
			@Override
			public boolean shouldOverrideUrlLoading(WebView view, String url) {
				// TODO Auto-generated method stub
				// 返回值是true的时候控制去WebView打开，为false调用系统浏览器或第三方浏览器

				if (url.indexOf("zwapp://showlist/?tab=zhoubian") != -1) {
					
					Toast.makeText(getApplicationContext(), "周边游", 1).show();
					
				} else if (url.indexOf("zwapp://showlist/?tab=gonglue") != -1) {
					
					Toast.makeText(getApplicationContext(), "旅游攻略", 1).show();
				} else if (url.indexOf("zwapp://showlist/?tab=zhaiguo") != -1) {
					
					Toast.makeText(getApplicationContext(), "摘果", 1).show();
				} else if (url.indexOf("zwapp://showlist/?tab=gongyuan") != -1) {
					
					Toast.makeText(getApplicationContext(), "主题公园", 1).show();
					
				} else {

				}
				return true;

			}
		});

	}

}
