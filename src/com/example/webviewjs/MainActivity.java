package com.example.webviewjs;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.webkit.JavascriptInterface;
import android.webkit.ValueCallback;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.Toast;

public class MainActivity extends Activity implements OnClickListener{


	WebView webView;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		  setContentView(R.layout.activity_main);
		  findViewById(R.id.btn1).setOnClickListener(this);
		  findViewById(R.id.btn2).setOnClickListener(this);
		  findViewById(R.id.btn3).setOnClickListener(this);
		  findViewById(R.id.btn4).setOnClickListener(this);
          initView();
	
	}
	private void initView() {
		// TODO Auto-generated method stub
		// 获取webview控件
			webView = (WebView) findViewById(R.id.activity_webview);
			// 获取WebView的设置
			WebSettings webSettings = webView.getSettings();
			//设置编码
			webSettings.setDefaultTextEncodingName("utf-8");
	        //设置背景颜色 透明
			webView.setBackgroundColor(Color.argb(0, 0, 0, 0));
			// 将JavaScript设置为可用，这一句话是必须的，不然所做一切都是徒劳的
			webSettings.setJavaScriptEnabled(true);
			// 通过webview加载html页面
			webView.loadUrl("file:///android_asset/ll.html");
			// 给webview添加JavaScript接口
		    webView.addJavascriptInterface(new JsInterface(), "obj");
	}

	@SuppressLint("NewApi") @Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.btn1:
			webView.loadUrl("javascript:funFromjs()");
			break;
		case R.id.btn2:
			webView.loadUrl("javascript:funJs('Android端传入的信息，div标签中显示,含参数')");
			break;
		case R.id.btn3:
			webView.loadUrl("javascript:sum(6,6)");
			break;
		case R.id.btn4:
			/***
			 * Android 4.4之后使用evaluateJavascript即可。这里展示一个简单的交互示例
			 * 
			 */
		 webView.evaluateJavascript("sumn(6,11)", new ValueCallback<String>() {
		            @Override
		            public void onReceiveValue(String value) {
		                Toast.makeText(MainActivity.this, "返回值"+value, Toast.LENGTH_SHORT).show();
		            }
		        });
			

			break;

		default:
			break;
		}
	}
	public class JsInterface {
		//JS中调用Android中的方法 和返回值处理的一种方法
		
		/****
		 * Html中的点击事件 onclick
		 *  <input type="button" value="结算" onclick="showToast('12')">
		 * @param toast
		 */
		@JavascriptInterface
		public void showToast(String toast) {
			Toast.makeText(MainActivity.this, "你的商品价格是：￥"+toast, Toast.LENGTH_SHORT).show();
		}
		/***
		 * Android代码调用获取J是中的返回值
		 * 
		 * @param result
		 */
		@JavascriptInterface
		public void onSum(int result) { 
			Toast.makeText(MainActivity.this, "Android调用JS方法且有返回值+计算结果=="+result, Toast.LENGTH_SHORT).show();
		} 
		
	}
	

}

