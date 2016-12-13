# Android 中的 WebView实现Html5视屏标签网页加载

<p>自Android 4.4起，Android中的WebView开始基于Chromium(谷歌浏览器)支持浏览器的一系列功能，webkit解析网页各个节点，这个改变，使得WebView的性能大幅度提升，并且对HTML5, CSS3, and JavaScript有了更好的支持。</p> 
<p>案列主要介绍WebView加载带有HTML5的视频标签的网页，点击网页里面的链接还是在当前的webview里跳转，不跳到浏览器那边，防止WebView内存泄漏等。</p> 
<p>效果图：</p> 
<p>&nbsp;&nbsp;<img alt="" src="http://images2015.cnblogs.com/blog/1041439/201612/1041439-20161213161219042-1977675010.gif"></p> 
<p>Html网页图：</p> 
<p>&nbsp;<img alt="" src="http://images2015.cnblogs.com/blog/1041439/201612/1041439-20161213161344276-957293590.png"></p> 
<p>代码</p> 
<pre><code class="language-java">public class MainActivity extends Activity {

	private WebView webView;
	private String url = "http://lbh.zhangwoo.cn/?m=home&amp;c=index&amp;a=home";

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
		if ((keyCode == KeyEvent.KEYCODE_BACK) &amp;&amp; webView.canGoBack()) {
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
</code></pre> 
<p>网络权限</p> 
<pre><code class="language-html">&lt;uses-permission android:name="android.permission.INTERNET"/&gt;</code></pre> 
<p><strong><span style="color:#B22222">源码下载</span>：<a href="http://download.csdn.net/detail/dickyqie/9710251" target="_blank" rel="nofollow">http://download.csdn.net/detail/dickyqie/9710251</a></strong></p>
