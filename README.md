# WebView 实现JS效果和a标签的点击事件
<div class="blog-body" id="blogBody">
                                    <val data-name="blog_content_type" data-value="richtext"></val>
                    <div class='BlogContent'>
                        <p>目前很多android app都可以显示web页面的界面，嵌入式开发，这个界面一般都是WebView这个控件加载出来的，学习该控件可以为你的app开发提升扩展性。</p> 
<p>先说下WebView的一些优点：</p> 
<ol> 
 <li>可以直接显示和渲染web页面，直接显示网页</li> 
 <li>webview可以直接用html文件（网络上或本地assets中）作布局</li> 
 <li>和JavaScript交互调用</li> 
 <li>网页标签的点击事件</li> 
</ol> 
<p>效果：（网页顶部是JS效果滚动，4个模块可以实现点击事件，可看到信息提示）</p> 
<p>&nbsp; &nbsp; &nbsp; &nbsp;<img alt="" src="http://images2015.cnblogs.com/blog/1041439/201612/1041439-20161208182121304-1087924997.gif"></p> 
<p>&nbsp;</p> 
<p>activity_main.xml</p> 
<pre><code class="language-html">&lt;RelativeLayout xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:tools="http://schemas.android.com/tools"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    tools:context="${relativePackage}.${activityClass}" &gt;

   	&lt;WebView 
	       android:layout_width="match_parent"
	    	android:layout_height="match_parent"
	    	android:id="@+id/webview"
	        /&gt;

&lt;/RelativeLayout&gt;</code></pre> 
<p>MainActivity.java</p> 
<pre><code class="language-java">public class MainActivity extends Activity {

	public String URL = "http://bajie.zhangwoo.cn/app.php?platform=android&amp;appkey=5a379b5eed8aaae531df5f60b12100cfb6dff2c1&amp;c=travel&amp;a=home";
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
</code></pre> 
<p>&nbsp;</p> 
<p>记得加网络权限</p> 
<pre><code class="language-html">&lt;uses-permission android:name="android.permission.INTERNET"/&gt;</code></pre> 
<p>&nbsp;</p> 
