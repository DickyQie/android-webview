# Android Webview 和Javascript交互，实现Android和JavaScript相互调用 
 <p>在Android的开发过程中、遇到一个新需求、那就是让Java代码和Javascript代码进行交互、在IOS中实现起来很麻烦、而在Android中相对来说容易多了、Android对这种交互进行了很好的封装、我们可以很简单的用Java代码调用WebView中的js函数、也可以用WebView中的js来调用Android应用中的Java代码。</p> 
<p>案例主要包含了：</p> 
<ol> 
 <li>&nbsp;Html中调用Android方法</li> 
 <li>Android调用JS方法无参数</li> 
 <li>Android调用JS方法有参数</li> 
 <li>Android调用JS方法有参数且有返回值处理方式1</li> 
 <li>Android调用JS方法有参数且有返回值处理方式2（Android4.4以上）</li> 
</ol> 
<p>1：创建JS对象</p> 
<pre><code class="language-java">webView.addJavascriptInterface(new JsInterface(), "obj");</code></pre> 
<pre><code class="language-java">public class JsInterface {
	//JS中调用Android中的方法 和返回值处理的一种方法
		
	/****
          * Html中的点击事件 onclick
	  *  &lt;input type="button" value="结算" onclick="showToast('12')"&gt;
	  * @param toast
	  */
	@JavascriptInterface
	public void showToast(String toast) {
	  Toast.makeText(MainActivity.this, "你的商品价格是：￥"+toast, Toast.LENGTH_SHORT).show();
	}
}</code></pre> 
<pre><code class="language-javascript"> function showToast(toast) { 
	var money=toast*3;
	javascript:obj.showToast(money);
}</code></pre> 
<p>2：</p> 
<pre><code class="language-java">webView.loadUrl("javascript:funFromjs()");</code></pre> 
<pre><code class="language-javascript">function funFromjs(){
    document.getElementById("helloweb").innerHTML="div显示数据,无参数";
}</code></pre> 
<p>3：</p> 
<pre><code class="language-java">webView.loadUrl("javascript:funJs('Android端传入的信息，div标签中显示,含参数')");</code></pre> 
<pre><code class="language-javascript">function funJs(msg){
   document.getElementById("hello2").innerHTML=msg;
}</code></pre> 
<p>4：&nbsp;</p> 
<pre><code class="language-java">webView.loadUrl("javascript:sum(6,6)");</code></pre> 
<pre><code class="language-java">/***
 * Android代码调用获取J是中的返回值
 * 
 * @param result
*/
   @JavascriptInterface
   public void onSum(int result) { 
	Toast.makeText(MainActivity.this, "Android调用JS方法且有返回值+计算结果=="+result, Toast.LENGTH_SHORT).show();
   } </code></pre> 
<pre><code class="language-javascript">function sum(i,m){ 
    var result = i*m; 
    document.getElementById("h").innerHTML= "Android调用JS方法且有返回值--计算结果="+result; 
    javascript:obj.onSum(result) 
} </code></pre> 
<p>5：</p> 
<pre><code class="language-java"> webView.evaluateJavascript("sumn(6,11)", new ValueCallback&lt;String&gt;() {
         @Override
	 public void onReceiveValue(String value) {
	     Toast.makeText(MainActivity.this, "返回值"+value, Toast.LENGTH_SHORT).show();
           }
});</code></pre> 
<pre><code class="language-javascript">function sumn(i,m){ 
     var result = i*m; 
     document.getElementById("hh").innerHTML= "Android调用JS方法且有返回值--计算结果="+result; 
     return result;
} </code></pre> 
<p>&nbsp;&nbsp; 注意：</p> 
<p>1、Java 调用 js 里面的函数、效率并不是很高、估计要200ms左右吧、做交互性很强的事情、这种速度很难让人接受、而js去调Java的方法、速度很快、50ms左右、所以尽量用js调用Java方法</p> 
<p>2、Java 调用 js 的函数、没有返回值、调用了就控制不到了</p> 
<p>3、Js 调用 Java 的方法、返回值如果是字符串、你会发现这个字符串是 native 的、转成 locale 的才能正常使用、使用 toLocaleString() 函数就可以了、不过这个函数的速度并不快、转化的字符串如果很多、将会很耗费时间</p> 
<p>4、网页中尽量不要使用jQuery、执行起来需要5-6秒、最好使用原生的js写业务脚本、以提升加载速度、改善用户体验。</p> 
<p>注：使用的是本地的Html文件，不过在网络链接的Html文件也是可以实现的。 &nbsp;&nbsp;</p> 
