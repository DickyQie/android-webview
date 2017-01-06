package com.example.webviewhttpcookiedemo;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		initView();
		
	}
	private void initView() {
		// TODO Auto-generated method stub
		findViewById(R.id.btn1).setOnClickListener(new OnClickListener() {
					@Override
					public void onClick(View v) {
						// TODO Auto-generated method stub
						new Thread(new Runnable() {
							
							@Override
							public void run() {
								// TODO Auto-generated method stub
								String str="platform=android&appkey=40a3e8e50fa27b8e6f1dd1a4b7454a0a&version=1.0&c=member&a=login&account=18748819480&password=123456";
								String ss=HttpConnection.request("", str, MainActivity.this);
								Log.i("http",ss);
							}
						}).start();
					}
				});
		findViewById(R.id.btn2).setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				startActivity(new Intent(MainActivity.this,PageMy.class));
			}
        });
	}
	
}
