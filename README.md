# 兰大邮箱 WebView

一个极简 Android WebView 应用，启动后直接打开 `https://mail.lzu.edu.cn/`。

## 功能

- 启动后加载兰州大学邮箱登录页
- 开启 JavaScript、DOM Storage、Cookie 和第三方 Cookie
- 去掉 WebView User-Agent 中容易被网页识别并拦截的 `wv` 标记
- 支持网页内返回、附件上传文件选择、附件下载
- 加载失败时显示重试按钮，不再无提示白屏
- 使用 `ALi.jpg` 生成应用图标

## 使用

用 Android Studio 打开本目录，等待 Gradle 同步完成后运行 `app`。

首次同步会下载 Android Gradle Plugin，因此需要能访问 `google()` 和 `mavenCentral()`。

## 没有 Android Studio 时打包 APK

也可以用 GitHub Actions 云端打包：

1. 把本目录所有文件上传到 GitHub 仓库
2. 打开仓库的 `Actions`
3. 选择 `Build APK`
4. 点击 `Run workflow`
5. 运行完成后，在页面底部 `Artifacts` 下载 `lzu-mail-debug-apk`

下载后解压即可得到 `app-debug.apk`，这个版本适合先安装测试。
