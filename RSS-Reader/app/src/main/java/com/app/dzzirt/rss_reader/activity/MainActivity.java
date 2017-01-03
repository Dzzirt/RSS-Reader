package com.app.dzzirt.rss_reader.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;

import com.app.dzzirt.rss_reader.BuildConfig;
import com.app.dzzirt.rss_reader.R;
import com.app.dzzirt.rss_reader.common.RSSItem;
import com.app.dzzirt.rss_reader.common.RssItemAdapter;
import com.app.dzzirt.rss_reader.presenter.RssFeedPresenter;
import com.app.dzzirt.rss_reader.view.RssFeedView;
import com.arellomobile.mvp.MvpAppCompatActivity;
import com.arellomobile.mvp.presenter.InjectPresenter;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;

@EActivity(R.layout.activity_main)
public class MainActivity extends MvpAppCompatActivity implements RssFeedView {

    @ViewById(R.id.feed_toolbar)
    protected Toolbar m_toolbar;

    @ViewById(R.id.feed_recyclerview)
    protected RecyclerView m_feedList;

    @InjectPresenter
    RssFeedPresenter m_rssFeedPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }


    @AfterViews
    protected void init() {
        setSupportActionBar(m_toolbar);
        m_rssFeedPresenter.onInjectFeedList();
    }

    @Override
    public void showRssItemInfo(RSSItem item) {
        //go to rss item preview activity
        finish();
    }

    @Override
    public void initFeedList(RecyclerView.Adapter adapter) {
        setLayoutManagerByDeviceType();
        m_feedList.setAdapter(adapter);
    }

    private void setLayoutManagerByDeviceType() {
        if (getResources().getBoolean(R.bool.isTablet)) {
            m_feedList.setLayoutManager(new LinearLayoutManager(this));
        } else {
            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
            DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(
                    m_feedList.getContext(),
                    linearLayoutManager.getOrientation());
            m_feedList.setLayoutManager(linearLayoutManager);
            m_feedList.addItemDecoration(dividerItemDecoration);
        }
    }
}
