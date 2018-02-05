### Andriod中WebView加载登录界面获取Cookie信息并同步保存,使第二次不用登录也可查看个人信息。
<p>Andriod中WebView加载登录界面获取Cookie信息并同步保存,使第二次不用登录也可查看个人信息。

Android使用WebView加载登录的html界面，则通过登录成功获取Cookie并同步，可以是下一次不用登录也可以查看到个人信息，注：如果初始化加载登录，可通过缓存Cookie信息来验证是否要加载登录界面。Cookie信息包含了你登录的信息，打印是是这种格式 </p> 
<p><img alt="" height="354" src="https://static.oschina.net/uploads/space/2017/0107/112211_XyQZ_2945455.png" width="757"></p> 
<p>包含各种编码，16进制等，可通过转码查看信息（包含当前城市，IP，个人相关信息等），转码后如图：</p> 
<p>&nbsp; &nbsp;&nbsp;<img alt="" height="505" src="https://static.oschina.net/uploads/space/2017/0109/112210_9qOY_2945455.png" width="676"></p> 
<p>项目效果图：</p> 
<p><img alt="" height="362" src="https://static.oschina.net/uploads/space/2017/0109/112638_x1M4_2945455.gif" width="340"></p> 
