# union
Appium的安装：
1.	安装nodejs 0.8版本及以上，设置好nodejs环境变量，cmd输入node –v出现版本，则说明安装完成。
2.	安装android的sdk包，(http://developer.android.com/sdk/index.html), 运行依赖sdk中的’android’工具。并确保你安装了Level17或以上的版本api。设置ANDROID_HOME系统变量为你的Android SDK路径，并把tools platform-tools两个目录加入到系统的Path路径里。因为这里面包含有一些执行命令。以我自己机器为例：PATH中新建下面变量：ANDROID_HOME：E:\Program Files (x86)\Android\android-sdk；
ANDROID_PLATFORM_TOOLS：E:\Program Files (x86)\Android\android-sdk\platform-tools；
ANDROID_TOOLS：E:\Program Files (x86)\Android\android-sdk\tools；新建完后，在PATH中加入下面变量。
   
Cmd中输入 adb devices。可以看到打开usb调试的设备，为设备uuid号。
3.	安装java的JDK，并设置JAVA_HOME 变量为你的JDK目录。
4.	通过npm install -g appium安装Appium（或者通过附件中的安装包安装）
安装完成后：
在PATH中添加变量： 
打开cmd输入appium：出现log日志说明安装完成
