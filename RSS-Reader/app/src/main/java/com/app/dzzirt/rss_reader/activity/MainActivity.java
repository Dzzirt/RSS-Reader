package com.app.dzzirt.rss_reader.activity;

import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.text.Html;
import android.widget.Toast;

import com.app.dzzirt.rss_reader.R;
import com.app.dzzirt.rss_reader.common.RssItemAdapter;
import com.app.dzzirt.rss_reader.common.RssItemManager;
import com.app.dzzirt.rss_reader.greendao.RssItem;
import com.app.dzzirt.rss_reader.greendao.RssItemDao;
import com.app.dzzirt.rss_reader.presenter.RssFeedPresenter;
import com.app.dzzirt.rss_reader.view.RssFeedView;
import com.arellomobile.mvp.MvpAppCompatActivity;
import com.arellomobile.mvp.presenter.InjectPresenter;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@EActivity(R.layout.activity_main)
public class MainActivity extends MvpAppCompatActivity implements RssFeedView {

    @ViewById(R.id.feed_toolbar)
    protected Toolbar m_toolbar;

    @ViewById(R.id.feed_recyclerview)
    protected RecyclerView m_feedList;

    @ViewById(R.id.pullToRefreshLayout)
    protected SwipeRefreshLayout m_swipeRefreshLayout;

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
        m_swipeRefreshLayout.setColorSchemeResources(R.color.colorThumbnailBorder);
        m_swipeRefreshLayout.setOnRefreshListener(() -> m_rssFeedPresenter.onRefresh());
    }

    @Override
    public void showRssItemInfo(RssItem item) {
        //go to rss item preview activity
        finish();
    }

    @Override
    public void initFeedList(RecyclerView.Adapter adapter) {
        setLayoutManagerByDeviceType();
        m_feedList.setAdapter(adapter);
    }

    @Override
    public void updateFeedData(List<RssItem> items) {
        RssItemAdapter adapter = (RssItemAdapter) m_feedList.getAdapter();
        adapter.clear();
        adapter.addAll(items);
    }

    @Override
    public void resetRefreshing() {
        m_swipeRefreshLayout.setRefreshing(false);
    }

    @Override
    public void showErrorRefreshingMessage() {
        Toast.makeText(this, R.string.refreshing_error, Toast.LENGTH_LONG).show();
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
