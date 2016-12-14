#WebView加载本地Html文件并实现点击效果
<p>Webview是用来与前端交互的纽，可以加载本地Html文件，和网页并实现交互的功能。</p> 
<p>WebView通过WebSetting可以使用Android原生的JavascriptInterface来进行js和java的通信。</p> 
<p>加载本地文件：webView.loadUrl("file:///android_asset/xxx.html");</p> 
<p>加载网页：webView.loadUrl("http://baidu.com");</p> 
<p>案例：（WebView加载本地Html并实现与JS通信） 效果图：</p> 
<p>&nbsp; &nbsp; &nbsp; &nbsp;<img alt="" height="472" src="https://static.oschina.net/uploads/space/2016/1214/111717_A2wv_2945455.gif" width="259"></p> 
<p>代码：</p> 
<pre><code class="language-java">/***
 * 
 * WebView加载本地文件和实现JS点击效果
 * 
 * @author zq
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
</code></pre> 
<p>JS文件</p> 
<pre><code class="language-javascript">  function showToast(toast) {       
	  javascript:control.showToast(toast);
     }
  function log(msg) {
	  consolse.log(msg);
    }
</code></pre> 
<p>AndroidManifest.xml&nbsp;中加权限</p> 
<pre><code class="language-html">&lt;uses-permission android:name="android.permission.INTERNET"/&gt;
&lt;uses-permission android:name="android.permission.ACCESS_NETWORK_STATE"/&gt;  </code></pre> 
<p><strong>代码未完全给出，要源码直接下载即可</strong></p> 
<p>&nbsp;</p> 
