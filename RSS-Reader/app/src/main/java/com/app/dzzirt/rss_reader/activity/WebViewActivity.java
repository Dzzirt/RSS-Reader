package com.app.dzzirt.rss_reader.activity;

import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.app.dzzirt.rss_reader.R;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.Extra;
import org.androidannotations.annotations.ViewById;

/**
 * Created by Dzzirt on 08.01.2017.
 */
@EActivity(R.layout.webview_activity)
public class WebViewActivity extends AppCompatActivity {

    @Extra
    String link;

    @ViewById(R.id.webview_toolbar)
    Toolbar m_toolbar;

    @ViewById(R.id.webview)
    WebView m_webview;

    @AfterViews
    void init() {
        initToolbar();
        m_webview.loadUrl(link);
    }

    private void initToolbar() {
        setSupportActionBar(m_toolbar);
        m_toolbar.setNavigationOnClickListener(v -> onBackPressed());
    }
}
