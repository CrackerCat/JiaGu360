# JiaGu360
[ ![Download](https://api.bintray.com/packages/zf/maven/JiaGu360/images/download.svg) ](https://github.com/903600017/JiaGu360/release)
[![License](https://img.shields.io/badge/License-Apache%202.0-blue.svg)](https://raw.githubusercontent.com/903600017/JiaGu360/master/LICENSE)




JiaGu360 根据360加固命令实现app快捷加固的插件，解放双手，实现自动化流程。


### Gradle插件使用方式


#### 下功360加固软件 

[360加固软件下载地址](http://jiagu.360.cn/#/global/download)
![360加固软件界面](https://raw.githubusercontent.com/903600017/JiaGu360/master/pic/360jiagu_software.png)


#### 360加固 jiagu.jar，

![jiagu.jar文件路径](https://raw.githubusercontent.com/903600017/JiaGu360/master/pic/360jiagu_software_dir.png)

#### 360加固 多渠道配置文件，

![jiagu.jar文件路径](https://raw.githubusercontent.com/903600017/JiaGu360/master/pic/360jiagu_mul_channel.png)


#### 配置build.gradle

在位于项目的根目录 `build.gradle` 文件中添加 ApkSign插件的依赖， 如下：

```groovy
buildscript {
    dependencies {
        classpath 'com.zf.plugins:JiaGu360:1.0.1'
    }
}
```

并在当前App的 `build.gradle` 文件中apply这个插件

```groovy
apply plugin: 'jiagu360'
```

#### 配置插件(最简易配置)

```groovy
jiaGu360Config {
	//360用户名
    userName "XXXXXX"
	//360密码
    passWord "XXXXXX"
	//360加固jiagu.jar包位置
	jiaGuJarPath new File("D:\\XXXXX\\360jiagubao_windows_64\\jiagu\\jiagu.jar").absolutePath
	
    items {
        debug {
      		//需要签名的APK 路径
            inputApkFilePath file("build/outputs/apk/tap_unsign.apk").absolutePath
        }
		// ...... 可以添加更多选项
    }
}
```

#### 插件全部配置
```groovy

jiaGu360Config {
	//360用户名
    userName "XXXXXX"
	//360密码
    passWord "XXXXXX"
	//360加固jar包位置
	jiaGuJarPath new File("D:\\XXXXX\\360jiagubao_windows_64\\jiagu\\jiagu.jar").absolutePath
	
	//加固配置项服务是否都支持
    isSupportAll false

//统一配置 优先低于自定义配置--------------------------start----------------------------------------------------------------------

	//android 签名配置名称，默认android默认的'debug'签名配置，signingName="debug"
	signingName 'debug'
	//加固apk的输出目录
    outputApkDirPath new File("D:\\XXXXX\\360jiagubao_windows_64\\jiagu\\XXXX").absolutePath
	//加固完成后是否打开输出目录。默认false
    openOutputDir false
	
	
// 加固配置项服务-------------------------------start-------------------------------------

	// 可选增强服务--------------start----------------------
    isSupportCrashLong false //【崩溃日志分析】
    isSupportX86 false //【x86支持】
    isSupportAnalyse false//【加固数据分析】
    isNocert false//【跳过签名校验】
    // 可选增强服务--------------end----------------------

    //高级加固选项-------------start------------------
    isSupportVmp false//【全VMP保护】
    isSupportDataProtection false//【本地数据文件保护】
    isSupportAssetsProtection false// 【资源文件保护】
    isSupportFileCheck false//【文件完整性校验】
    isSupportPtrace false//【Ptrace防注入】
    isSupportSoProtection false//【SO文件保护】
    isSupportDex2cProtection false//【dex2C保护】
    isSupportStringObfusProtection false//【字符串加密】
    isSupportDexShadowProtection false//【DexShadow】
    isSupportSoPrivateProtection false//【SO防盗用】
    //高级加固选项-------------end------------------
	
// 加固配置项服务--------------------------------end---------------------------------------------

	//自动签名
     autosign false

	//自定义文件生成多渠道，可以根据前面下载的360加固软件里的 “多渠道模板.txt” 编写
     mulpkgFilePath =new File("D:\\XXXXX\\360jiagubao_windows_64\\jiagu\\多渠道模板.txt")
	//签名配置项
     signingInfo {
        storeFilePath "E:\\Document\\XXXXXX.jks"
        storePassword "XXXXXX"
        keyAlias "XXXXXX"
        keyPassword "XXXXXX"
    }
	
//统一配置 --------------------------end----------------------------------------------------------------------

	items {
        release {
      		//需要加固的APK 路径
            inputApkFilePath file("build/outputs/apk/XXXX.apk").absolutePath
//自定义配置-----------------------------start-----------------------------------------------------------------------
			//android 签名配置名称，默认android默认的'debug'签名配置，signingName="debug"
			signingName 'debug'
			//加固apk的输出目录
			outputApkDirPath new File("D:\\XXXXX\\360jiagubao_windows_64\\jiagu\\XXXX").absolutePath
			//加固完成后是否打开输出目录。默认false
			openOutputDir false

		// 加固配置项服务-------------------------------start-------------------------------------

			// 可选增强服务--------------start----------------------
			isSupportCrashLong false //【崩溃日志分析】
			isSupportX86 false //【x86支持】
			isSupportAnalyse false//【加固数据分析】
			isNocert false//【跳过签名校验】
			// 可选增强服务--------------end----------------------

			//高级加固选项-------------start------------------
			isSupportVmp false//【全VMP保护】
			isSupportDataProtection false//【本地数据文件保护】
			isSupportAssetsProtection false// 【资源文件保护】
			isSupportFileCheck false//【文件完整性校验】
			isSupportPtrace false//【Ptrace防注入】
			isSupportSoProtection false//【SO文件保护】
			isSupportDex2cProtection false//【dex2C保护】
			isSupportStringObfusProtection false//【字符串加密】
			isSupportDexShadowProtection false//【DexShadow】
			isSupportSoPrivateProtection false//【SO防盗用】
			//高级加固选项-------------end------------------

		// 加固配置项服务--------------------------------end---------------------------------------------

			//自动签名
			 autosign false

			//自定义文件生成多渠道，可以根据前面下载的360加固软件里的 “多渠道模板.txt” 编写
			mulpkgFilePath =new File("D:\\XXXXX\\360jiagubao_windows_64\\jiagu\\多渠道模板.txt")
			//签名配置项
			 signingInfo {
				storeFilePath "E:\\Document\\XXXXXX.jks"
				storePassword "XXXXXX"
				keyAlias "XXXXXX"
				keyPassword "XXXXXX"
			}
//自定义配置-----------------------------end-----------------------------------------------------------------------
        }
		
		debug {
      		//需要加固的APK 路径
            inputApkFilePath file("build/outputs/apk/XXXX.apk").absolutePath
        }
		// ...... 可以添加更多选项
    }
}
```


**配置项具体解释：**

* 当 “统一配置” ，“自定义配置”  设置的参数都存在时， **自定义配置 > 统一配置 , 这是总的原则**

* 当`isSupportAll=true`时 ,统一配置里的可配置服务全部都支持

*  `signingInfo` ,`signingName`都配置时,优化级为 `signingInfo` > `signingName`;当两个配置项都不配置时，默认使用 android项目里的默认debug签名。

*  `signingName='release'` 签名信息配置的名称,
                  
	![签名配置名称](https://raw.githubusercontent.com/903600017/JiaGu360/master/pic/sign_config_name.png)
	
	**生成apk加固包：**
	
	`./gradlew jiagu360${item配置名称(首页字母大小)}  `
	
	![item配置名称](https://raw.githubusercontent.com/903600017/JiaGu360/master/pic/item_config_name.png)

	 
	 如上面的配置，生成签名包需要执行如下命令：
	 
	 `./gradlew apkSignRelease `

	**360加固升级命令：**
	 `./gradlew jiagu360Update `

## Q&A
- [输出乱码](https://github.com/903600017/JiaGu360/wiki/Terminal-%E8%BE%93%E5%87%BA%E4%B9%B1%E7%A0%81%EF%BC%9F)？

## 技术支持

* Read The Fucking Source Code
* 通过提交issue来寻求帮助
* 联系我们寻求帮助.(QQ群：366399995)

## 贡献代码
* 欢迎提交issue
* 欢迎提交PR


## License

    Copyright 2017 903600017

    Licensed under the Apache License, Version 2.0 (the "License");
    you may not use this file except in compliance with the License.
    You may obtain a copy of the License at

       http://www.apache.org/licenses/LICENSE-2.0

    Unless required by applicable law or agreed to in writing, software
    distributed under the License is distributed on an "AS IS" BASIS,
    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
    See the License for the specific language governing permissions and
    limitations under the License.
