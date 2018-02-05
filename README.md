### Android WebView 实现缓存网页数据
  <p>WebView中存在着两种缓存：网页数据缓存（存储打开过的页面及资源），H5缓存（即AppCache）。</p> 
<p>将我们浏览过的网页url已经网页文件(css、图片、js等)保存到数据库表中</p> 
<p><strong>缓存模式(5种)</strong><br> LOAD_CACHE_ONLY: &nbsp;不使用网络，只读取本地缓存数据<br> LOAD_DEFAULT: &nbsp;根据cache-control决定是否从网络上取数据。<br> LOAD_CACHE_NORMAL: API level 17中已经废弃, 从API level 11开始作用同LOAD_DEFAULT模式<br> LOAD_NO_CACHE: 不使用缓存，只从网络获取数据.<br> LOAD_CACHE_ELSE_NETWORK，只要本地有，无论是否过期，或者no-cache，都使用缓存中的数据。<br> 如：www.taobao.com的cache-control为no-cache，在模式LOAD_DEFAULT下，无论如何都会从网络上取数据，如果没有网络，就会出现错误页面；在LOAD_CACHE_ELSE_NETWORK模式下，无论是否有网络，只要本地有缓存，都使用缓存。本地没有缓存时才从网络上获取。<br> www.360.com.cn的cache-control为max-age=60，在两种模式下都使用本地缓存数据。<br> <br> 总结：根据以上两种模式，建议缓存策略为，判断是否有网络，有的话，使用LOAD_DEFAULT，无网络时，使用LOAD_CACHE_ELSE_NETWORK。</p> 
<p>效果图：</p> 
<p>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<img alt="" height="608" src="http://images2015.cnblogs.com/blog/1041439/201612/1041439-20161213180025167-752240917.gif" width="379"></p> 
<p>根据百度首页来测试的，有网的情况下加载，之后关闭网络并结束进程在打开一样可以看到先前看到的网页，当点击没打开过的网页是则：</p> 
<p>&nbsp; &nbsp; &nbsp; &nbsp;<img alt="" height="265" src="http://images2015.cnblogs.com/blog/1041439/201612/1041439-20161213180049651-1835223746.png" width="302"></p> 
<p>缓存的数据目录默认在：data/data/包名/app_webview/Cache/ 下：</p> 
<p>&nbsp; &nbsp; &nbsp; &nbsp;<img alt="" height="317" src="http://images2015.cnblogs.com/blog/1041439/201612/1041439-20161213180103401-1926339804.png" width="344"></p> 

