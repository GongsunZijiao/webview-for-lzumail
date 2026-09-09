package cn.edu.lzu.mailwebview;

import android.app.Activity;
import android.os.Bundle;
import android.webkit.CookieManager;
import android.webkit.WebChromeClient;
import android.webkit.WebResourceError;
import android.webkit.WebResourceRequest;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class MainActivity extends Activity {

    private WebView webView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        webView = new WebView(this);
        setContentView(webView);

        WebSettings settings = webView.getSettings();

        // Coremail 页面需要
        settings.setJavaScriptEnabled(true);
        settings.setDomStorageEnabled(true);
        settings.setDatabaseEnabled(true);

        // 页面显示
        settings.setUseWideViewPort(true);
        settings.setLoadWithOverviewMode(true);

        // 缩放
        settings.setSupportZoom(true);
        settings.setBuiltInZoomControls(true);
        settings.setDisplayZoomControls(false);

        // 让网站把我们识别成普通手机浏览器，而不是特殊 WebView
        String ua = settings.getUserAgentString();
        ua = ua.replace("; wv", "");
        ua = ua.replace("Version/4.0 ", "");
        settings.setUserAgentString(ua);

        // Cookie，登录邮箱必须
        CookieManager cookieManager = CookieManager.getInstance();
        cookieManager.setAcceptCookie(true);
        cookieManager.setAcceptThirdPartyCookies(webView, true);

        // 支持 JavaScript 对话框等网页功能
        webView.setWebChromeClient(new WebChromeClient());

        webView.setWebViewClient(new WebViewClient() {

            @Override
            public boolean shouldOverrideUrlLoading(
                    WebView view,
                    WebResourceRequest request) {
                return false;
            }

            @Override
            public void onReceivedError(
                    WebView view,
                    WebResourceRequest request,
                    WebResourceError error) {

                if (request.isForMainFrame()) {
                    String message =
                            "<html><body style='font-family:sans-serif;padding:30px'>" +
                            "<h2>兰大邮箱加载失败</h2>" +
                            "<p>" + error.getDescription() + "</p>" +
                            "<p>请检查网络连接后重新打开 App。</p>" +
                            "</body></html>";

                    view.loadDataWithBaseURL(
                            null,
                            message,
                            "text/html",
                            "UTF-8",
                            null
                    );
                }
            }
        });

        if (savedInstanceState == null) {
            // 兰大 Coremail 手机端
            webView.loadUrl(
                    "https://mail.lzu.edu.cn/coremail/xphone/"
            );
        } else {
            webView.restoreState(savedInstanceState);
        }
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        webView.saveState(outState);
        super.onSaveInstanceState(outState);
    }

    @Override
    public void onBackPressed() {
        if (webView != null && webView.canGoBack()) {
            webView.goBack();
        } else {
            super.onBackPressed();
        }
    }

    @Override
    protected void onDestroy() {
        if (webView != null) {
            webView.destroy();
        }
        super.onDestroy();
    }
}
