package com.example.webviewhttpcookiedemo;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;

/****
 * 
 * @author zq 测试账号 name:DickyQie pwd:123456
 * 
 */
public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		initView();
	}

	private void initView() {
		findViewById(R.id.btn1).setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				new Thread(new Runnable() {
					@Override
					public void run() {
						// TODO Auto-generated method stub
						String str = "platform=android&appkey=40a3e8e50fa27b8e6f1dd1a4b7454a0a&version=1.0&c=member&a=login&account=DickyQie&password=123456";
						String request = HttpConnection.request("", str,
								MainActivity.this);
						Log.i("http", request);
						String cookie=CookieUtil.getParam(MainActivity.this).toString();
						Log.i("cookie", cookie);
						try {
							Log.i("转码后",  URLDecoder.decode(cookie, "utf-8"));
						} catch (UnsupportedEncodingException e) {
							e.printStackTrace();
						}
					}
				}).start();
			}
		});
		findViewById(R.id.btn2).setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				startActivity(new Intent(MainActivity.this, PageMy.class));
			}
		});
	}

}
