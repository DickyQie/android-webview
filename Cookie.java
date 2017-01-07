

	private void showCookie() {
		// TODO Auto-generated method stub
		webView.setWebViewClient(new WebViewClient() {
			@Override
			public boolean shouldOverrideUrlLoading(WebView view, String url) {
				// TODO Auto-generated method stub
				return super.shouldOverrideUrlLoading(view, url);
			}

			@Override
			public void onPageFinished(WebView view, String url) {
				CookieManager cookieManager = CookieManager.getInstance();
				String CookieStr = cookieManager.getCookie(url);
				if (CookieStr != null) {
					Log.i("cookie", CookieStr);
				}
				super.onPageFinished(view, url);
			}

		});
	}

	@SuppressLint("NewApi") public static String request(String httpUrl, String params, Context context) {
		BufferedReader reader = null;
		String result = null;
		String httpurl = " http://xwwscs.com";
		StringBuffer sbf = new StringBuffer();
		try {
			URL url = new URL(httpurl + "/app.php");
			HttpURLConnection connection = (HttpURLConnection) url
					.openConnection();
			connection.setRequestMethod("POST");
			connection.setRequestProperty("Cookie", CookieUtil
					.getParam(context).toString());
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
			Map<String, List<String>> cookie_map = connection.getHeaderFields();
			List<String> cookies = cookie_map.get("Set-Cookie");
			if (null != cookies && 0 < cookies.size()) {
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

