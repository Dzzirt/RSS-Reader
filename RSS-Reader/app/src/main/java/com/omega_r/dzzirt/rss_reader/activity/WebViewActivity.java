package com.omega_r.dzzirt.rss_reader.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.webkit.WebView;

import com.app.dzzirt.rss_reader.R;


import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Dzzirt on 08.01.2017.
 */
public class WebViewActivity extends BaseActivity {

    public static final String EXTRA_LINK = "link";

    @BindView(R.id.webview)
    WebView mWebView;

    @Override
    protected String getToolbarTitle() {
        return getString(R.string.browser);
    }

    public static Intent createIntent(Context context, String link) {
        Intent intent = new Intent(context, WebViewActivity.class);
        intent.putExtra(EXTRA_LINK, link);

        return intent;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.screen_browser);
        ButterKnife.bind(this);

        init();
    }

    void init() {
        String link = getIntent().getStringExtra(EXTRA_LINK);
        mWebView.loadUrl(link);
    }
}
