#Android中Http加载如何得到Cookie和 WebView 加载网页如何得到的Cookie
   <p>最近做项目在手机端登录Http请求和&nbsp;WebView 记载登录获取Cookie信息,可查看Cookie信息。</p> 
<p>如图：</p> 
<p><img alt="" height="354" src="https://static.oschina.net/uploads/space/2017/0107/112211_XyQZ_2945455.png" width="757"></p> 
<p>Http请求获取Cookie信息：</p> 
<pre><code class="language-java">public static String request(String httpUrl, String params, Context context) {
        BufferedReader reader = null;
        String result = null;
        String httpurl = " http://xwwscs.com";
        StringBuffer sbf = new StringBuffer();
        try {
            URL url = new URL(httpurl + "/app.php");
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("POST");
            connection.setRequestProperty("Cookie", CookieUtil.getParam(context).toString());
            connection.setConnectTimeout(3000);
            // 是否输入参数
            connection.setDoOutput(true);
            byte[] bypes = params.toString().getBytes();
            connection.getOutputStream().write(bypes);// 输入参数
           connection.connect();
            InputStream is = connection.getInputStream();
            reader = new BufferedReader(new InputStreamReader(is, "UTF-8"));
            String strRead = null;
            while ((strRead = reader.readLine()) != null) {
                sbf.append(strRead);
                sbf.append("\r\n");
            }
            reader.close();
            Map&lt;String, List&lt;String&gt;&gt; cookie_map = connection.getHeaderFields();
            List&lt;String&gt; cookies = cookie_map.get("Set-Cookie");
            if (null != cookies &amp;&amp; 0 &lt; cookies.size()) {
                String s = "";
                for (String cookie : cookies) {
                    if (s.isEmpty()) {
                        s = cookie;
                    } else {
                        s += ";" + cookie;
                    }
                }
                Log.i("cookie", s);
            }
            result = sbf.toString();
        } catch (Exception e) {
            result = "error";
            e.printStackTrace();
        }
        return result;
    }
}</code></pre> 
<p>WebView加载网页获取Cookie</p> 
<pre><code class="language-java"> webView.setWebViewClient(new WebViewClient()
                {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view,
                    String url) {
                // TODO Auto-generated method stub
                return super.shouldOverrideUrlLoading(view, url);
            }
            @Override
            public void onPageFinished(WebView view, String url) {
                CookieManager cookieManager = CookieManager.getInstance();
                String CookieStr = cookieManager.getCookie(url);
                if(CookieStr!=null)
                {
                    Log.i("cookie", CookieStr);
                }
                super.onPageFinished(view, url);
            }
            
          });</code></pre> 
<p>&nbsp;</p>
