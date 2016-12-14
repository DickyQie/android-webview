package com.example.webviewhtml;

import android.app.Activity;
import android.os.Bundle;
import android.webkit.JavascriptInterface;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.Toast;

/***
 * 
 * WebView加载本地文件和实现JS点击效果
 * 
 * @author Administrator
 *
 */
public class MainActivity extends Activity {

	private WebView webView;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		  setContentView(R.layout.activity_main);
          initView();
	
	}
	private void initView() {
		// TODO Auto-generated method stub
		  // 获取webview控件
			webView = (WebView) findViewById(R.id.activity_webview);
			// 获取WebView的设置
			WebSettings webSettings = webView.getSettings();
			// 将JavaScript设置为可用，这一句话是必须的，不然所做一切都是徒劳的
			webSettings.setJavaScriptEnabled(true);
			// 给webview添加JavaScript接口
			webView.addJavascriptInterface(new JsInterface(), "control");
			// 通过webview加载html页面
			webView.loadUrl("file:///android_asset/l.html");
	}

	public class JsInterface {
		@JavascriptInterface
		public void showToast(String toast) {
			Toast.makeText(MainActivity.this, toast, Toast.LENGTH_SHORT).show();
		}

		public void log(final String msg) {
			webView.post(new Runnable() {

				@Override
				public void run() {
					webView.loadUrl("javascript log(" + "'" + msg + "'" + ")");

				}
			});
		}
	}


}
