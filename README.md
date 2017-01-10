# Andriod的Http请求获取Cookie信息并同步保存,使第二次不用登录也可查看个人信息
   <p>Android使用Http请求登录，则通过登录成功获取Cookie信息并同步，可以是下一次不用登录也可以查看到个人信息，注：如果初始化加载登录，可通过缓存Cookie信息来验证是否要加载登录界面。Cookie信息包含了你登录的信息，打印是是这种格式 如下图：</p> 
<p><img alt="" height="354" src="https://static.oschina.net/uploads/space/2017/0107/112211_XyQZ_2945455.png" width="757"></p> 
<p>包含各种编码，16进制等，可通过转码查看信息（包含当前城市，IP，个人相关信息等），转码后如图：</p> 
<p>&nbsp; &nbsp;&nbsp;<img alt="" height="505" src="https://static.oschina.net/uploads/space/2017/0109/112210_9qOY_2945455.png" width="676"></p> 
<p>项目效果图：</p> 
<p><img alt="" height="362" src="https://static.oschina.net/uploads/space/2017/0110/111059_Jy1U_2945455.gif" width="340"></p> 
<p>代码：</p> 
<pre><code class="language-java">public class MainActivity extends Activity {

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
						String str = "platform=android&amp;appkey=40a3e8e50fa27b8e6f1dd1a4b7454a0a&amp;version=1.0&amp;c=member&amp;a=login&amp;account=DickyQie&amp;password=123456";
						String ss = HttpConnection.request("", str,
								MainActivity.this);
						Log.i("http", ss);
						Log.i("cookie", CookieUtil.getParam(MainActivity.this)
								.toString());
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
</code></pre> 
<p>&nbsp;</p> 
<p>不要忘记在AndroidManifest.xml加权限哦！</p> 
<pre><code class="language-html">&lt;uses-permission android:name="android.permission.INTERNET"/&gt;</code></pre> 
<span id="OSC_h2_1"></span>
