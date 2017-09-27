package com.omega_r.dzzirt.rss_reader.activity;

import android.arch.lifecycle.OnLifecycleEvent;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import com.app.dzzirt.rss_reader.R;
import com.omega_r.dzzirt.rss_reader.activity.adapter.FeedItemAdapter;
import com.omega_r.dzzirt.rss_reader.database.FeedItem;
import com.omega_r.dzzirt.rss_reader.viewmodel.FeedViewModel;
import com.omega_r.dzzirt.rss_reader.utils.DateUtils;
import com.omega_r.dzzirt.rss_reader.utils.ViewUtils;
import com.facebook.drawee.view.SimpleDraweeView;


import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

public class MainActivity extends BaseActivity {

    @BindView(R.id.recyclerview_feed)
    RecyclerView mFeedRecyclerView;

    @BindView(R.id.swiperefreshlayout)
    SwipeRefreshLayout mSwipeRefreshLayout;

    @BindView(R.id.simpledraweeview_thumbnail)
    SimpleDraweeView mThumbnail;

    @BindView(R.id.textview_title)
    TextView mTitleTextView;

    @BindView(R.id.textview_description)
    TextView mDescriptionTextView;

    @BindView(R.id.textview_author)
    TextView mAuthorTextView;

    @BindView(R.id.textview_date)
    TextView mDateTextView;

    @BindView(R.id.drawerlayout)
    DrawerLayout mDrawerLayout;

    private FeedViewModel mFeedViewModel;

    private String mItemLink;

    @Override
    protected String getToolbarTitle() {
        return getString(R.string.feed);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.screen_main);
        init();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_activity_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        super.onOptionsItemSelected(item);

        switch (item.getItemId()) {
            case R.id.action_settings:
                startActivity(SettingsActivity.createIntent(this));
                break;
        }
        return true;
    }

    protected void init() {
        setLayoutManagerByDeviceType();

        mFeedViewModel = ViewModelProviders.of(this).get(FeedViewModel.class);

        initFeedList();

        mFeedViewModel.getFeedObservable().observe(this, this::updateAdapter);

        mFeedViewModel.getFeedErrorObservable().observe(this, message -> {
            showErrorMessage(message);
            mSwipeRefreshLayout.setRefreshing(false);
        });

        mFeedViewModel.getSelectedItemObservable().observe(this, this::showRssItemInfo);

        initRefreshLayout();
    }

    private void updateAdapter(List<FeedItem> feedItems) {
        FeedItemAdapter adapter = (FeedItemAdapter) mFeedRecyclerView.getAdapter();
        adapter.setItems(feedItems);

        mSwipeRefreshLayout.setRefreshing(false);
    }

    private void initRefreshLayout() {
        mSwipeRefreshLayout.setColorSchemeResources(R.color.colorThumbnailBorder);
        mSwipeRefreshLayout.setOnRefreshListener(() -> mFeedViewModel.onRefresh());
    }

    private void initFeedList() {
        FeedItemAdapter adapter = new FeedItemAdapter();
        adapter.setOnItemClickListener(item -> mFeedViewModel.onFeedItemClick(item));
        mFeedRecyclerView.setAdapter(adapter);
    }

    @OnClick(R.id.button_view)
    void onViewButtonClick() {
        startActivity(WebViewActivity.createIntent(this, mItemLink));
        mDrawerLayout.closeDrawers();
    }

    private void setLayoutManagerByDeviceType() {
        if (isTablet()) {
            mFeedRecyclerView.setHasFixedSize(true);
            mFeedRecyclerView.setLayoutManager(new GridLayoutManager(
                    this,
                    ViewUtils.getSpanCount(this, R.dimen.rss_item_table_view_width)));

        } else {
            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
            DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(
                    mFeedRecyclerView.getContext(),
                    linearLayoutManager.getOrientation());
            mFeedRecyclerView.setLayoutManager(linearLayoutManager);
            mFeedRecyclerView.addItemDecoration(dividerItemDecoration);
        }
    }

    private boolean isTablet() {
        return getResources().getBoolean(R.bool.isTablet);
    }

    public void showRssItemInfo(FeedItem feedItem) {
        if (isTablet()) {
            openDrawer(feedItem);
        } else {
            startActivity(ItemInfoActivity.createIntent(this, feedItem.getId()));
        }
    }

    private void openDrawer(FeedItem feedItem) {
        mThumbnail.setImageURI(feedItem.getImageUrl());
        mTitleTextView.setText(feedItem.getTitle());
        mDescriptionTextView.setText(feedItem.getDescription());
        mAuthorTextView.setText(feedItem.getAuthor());
        mItemLink = feedItem.getUrl();
        mDateTextView.setText(DateUtils.asRegionalDateString(feedItem.getDate(), MainActivity.this));

        mDrawerLayout.openDrawer(Gravity.END);
    }
}
