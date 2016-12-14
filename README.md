# Android WebView 实现缓存网页数据
  <p>WebView中存在着两种缓存：网页数据缓存（存储打开过的页面及资源），H5缓存（即AppCache）。</p> 
<p>将我们浏览过的网页url已经网页文件(css、图片、js等)保存到数据库表中</p> 
<p><strong>缓存模式(5种)</strong><br> LOAD_CACHE_ONLY: &nbsp;不使用网络，只读取本地缓存数据<br> LOAD_DEFAULT: &nbsp;根据cache-control决定是否从网络上取数据。<br> LOAD_CACHE_NORMAL: API level 17中已经废弃, 从API level 11开始作用同LOAD_DEFAULT模式<br> LOAD_NO_CACHE: 不使用缓存，只从网络获取数据.<br> LOAD_CACHE_ELSE_NETWORK，只要本地有，无论是否过期，或者no-cache，都使用缓存中的数据。<br> 如：www.taobao.com的cache-control为no-cache，在模式LOAD_DEFAULT下，无论如何都会从网络上取数据，如果没有网络，就会出现错误页面；在LOAD_CACHE_ELSE_NETWORK模式下，无论是否有网络，只要本地有缓存，都使用缓存。本地没有缓存时才从网络上获取。<br> www.360.com.cn的cache-control为max-age=60，在两种模式下都使用本地缓存数据。<br> <br> 总结：根据以上两种模式，建议缓存策略为，判断是否有网络，有的话，使用LOAD_DEFAULT，无网络时，使用LOAD_CACHE_ELSE_NETWORK。</p> 
<p>效果图：</p> 
<p>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<img alt="" height="608" src="http://images2015.cnblogs.com/blog/1041439/201612/1041439-20161213180025167-752240917.gif" width="379"></p> 
<p>根据百度首页来测试的，有网的情况下加载，之后关闭网络并结束进程在打开一样可以看到先前看到的网页，当点击没打开过的网页是则：</p> 
<p>&nbsp; &nbsp; &nbsp; &nbsp;<img alt="" height="265" src="http://images2015.cnblogs.com/blog/1041439/201612/1041439-20161213180049651-1835223746.png" width="302"></p> 
<p>缓存的数据目录默认在：data/data/包名/app_webview/Cache/ 下：</p> 
<p>&nbsp; &nbsp; &nbsp; &nbsp;<img alt="" height="317" src="http://images2015.cnblogs.com/blog/1041439/201612/1041439-20161213180103401-1926339804.png" width="344"></p> 
<p>代码：</p> 
<pre><code class="language-java">public class MainActivity extends Activity {

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
        if ((keyCode == KeyEvent.KEYCODE_BACK) &amp;&amp; webView.canGoBack()) {
            webView.goBack(); // goBack()表示返回WebView的上一页面
            return true;
        } else {
            finish();
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

}</code></pre> 
<p>AndroidManifest.xml&nbsp;中加权限</p> 
<pre><code class="language-html">&lt;uses-permission android:name="android.permission.INTERNET"/&gt;
&lt;uses-permission android:name="android.permission.ACCESS_NETWORK_STATE"/&gt;   </code></pre> 
<p><strong>代码未完全给出，要源码直接下载即可</strong></p> 
