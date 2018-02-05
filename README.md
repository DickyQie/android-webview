### Android 中的 WebView实现Html5视屏标签网页加载

<p>自Android 4.4起，Android中的WebView开始基于Chromium(谷歌浏览器)支持浏览器的一系列功能，webkit解析网页各个节点，这个改变，使得WebView的性能大幅度提升，并且对HTML5, CSS3, and JavaScript有了更好的支持。</p> 
<p>案列主要介绍WebView加载带有HTML5的视频标签的网页，点击网页里面的链接还是在当前的webview里跳转，不跳到浏览器那边，防止WebView内存泄漏等。</p> 
<p>效果图：</p> 
<p>&nbsp;&nbsp;<img alt="" src="http://images2015.cnblogs.com/blog/1041439/201612/1041439-20161213161219042-1977675010.gif"></p> 
<p>Html网页图：</p> 
<p>&nbsp;<img alt="" src="http://images2015.cnblogs.com/blog/1041439/201612/1041439-20161213161344276-957293590.png"></p> 

