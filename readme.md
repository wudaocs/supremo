## supremo
supremo class

```
[app（应用主模块）] [tflutter（flutter模块）]

[base(基础能力提供包：包含基类、工具类方法)] [network（网络请求封装库）] [views（视图封装库）] 
``` 

### 集成flutter
flutter sdk 版本 1.5.4.hotfix-2

两种方式
- 1. 利用android studio 直接创建flutter module，会自动生成flutter相关配置信息
- 2. 步骤：（*！* 有一点需要注意的是flutterSDK中目前是固定写死应用module为app，如果有修改过名称的操作请注意这点）
  - 2.1. 在项目文件夹下执行命令  flutter create -t module [moduleName]
或者支持AndroidX flutter create --androidx -t module [moduleName]

  - 2.2 在项目根目录中的settings.gradle文件中增加flutter module项目配置信息
setBinding(new Binding([gradle: this]))
evaluate(new File(
        settingsDir.parentFile,
        //flutter_module即为创建的模块名称
        '[flutter_module]/.android/include_flutter.groovy'
))
  - 2.3 需要集成的项目添加依赖 implementation project(':flutter') [注意：flutter不可更改，并不是上一步定义的flutterModuleName]


其他：
1. 注意是gradle中的minSdkVersion必须要大于等于16，因为这个flutter支持的最低版本
2. 同时添加使用java8来编译
```
compileOptions {
    sourceCompatibility JavaVersion.VERSION_1_8
    targetCompatibility JavaVersion.VERSION_1_8
}
```
3. couldn't find "libflutter.so"问题？
buildTypes {
     release {
         ndk{
             abiFilters "armeabi"，"armeabi-v7a"
         }
     }
     debug {
         ndk {
             abiFilters "armeabi", "armeabi-v7a","arm64-v8a", "x86"
         }
     }
 }
 
 4. 1.5.4版本flutterSDK包含Flutter类，之后调整为GeneratedPluginRegistrant类

