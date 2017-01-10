package com.app.dzzirt.rss_reader.activity;

import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.widget.TextView;
import android.widget.Toast;

import com.app.dzzirt.rss_reader.R;
import com.app.dzzirt.rss_reader.common.RssItemAdapter;
import com.app.dzzirt.rss_reader.greendao.RssItem;
import com.app.dzzirt.rss_reader.presenter.RssFeedPresenter;
import com.app.dzzirt.rss_reader.presenter.RssItemInfoPresenter;
import com.app.dzzirt.rss_reader.utils.DateUtils;
import com.app.dzzirt.rss_reader.utils.NetUtils;
import com.app.dzzirt.rss_reader.utils.ViewUtils;
import com.app.dzzirt.rss_reader.view.RssFeedView;
import com.app.dzzirt.rss_reader.view.RssItemInfoView;
import com.arellomobile.mvp.MvpAppCompatActivity;
import com.arellomobile.mvp.presenter.InjectPresenter;
import com.facebook.drawee.view.SimpleDraweeView;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.OptionsItem;
import org.androidannotations.annotations.OptionsMenu;
import org.androidannotations.annotations.ViewById;

import java.util.List;

@EActivity(R.layout.activity_main)
@OptionsMenu(R.menu.menu_activity_main)
public class MainActivity extends MvpAppCompatActivity implements RssFeedView, RssItemInfoView {

    @ViewById(R.id.feed_toolbar)
    protected Toolbar m_toolbar;

    @ViewById(R.id.feed_recyclerview)
    protected RecyclerView m_feedList;

    @ViewById(R.id.pullToRefreshLayout)
    protected SwipeRefreshLayout m_swipeRefreshLayout;

    @ViewById(R.id.info_thumbnail)
    SimpleDraweeView m_thumbnail;

    @ViewById(R.id.info_title)
    TextView m_drawerTitle;

    @ViewById(R.id.info_description)
    TextView m_drawerDescription;

    @ViewById(R.id.info_author)
    TextView m_drawerAuthor;

    @ViewById(R.id.info_date)
    TextView m_drawerDate;

    @ViewById(R.id.item_info_drawer)
    DrawerLayout m_itemInfoDrawer;

    @InjectPresenter
    RssFeedPresenter m_rssFeedPresenter;

    @InjectPresenter
    RssItemInfoPresenter m_rssItemInfoPresenter;

    private String m_itemLink;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
    }

    @OptionsItem(R.id.action_settings)
    public void onActionSettingsSelected() {
        SettingsActivity_.intent(this).start();
    }

    @AfterViews
    protected void init() {
        setSupportActionBar(m_toolbar);
        initFeedList();
        m_swipeRefreshLayout.setColorSchemeResources(R.color.colorThumbnailBorder);
        m_swipeRefreshLayout.setOnRefreshListener(() -> {
            if (ensureNetworkConnection()) {
                m_rssFeedPresenter.onRefresh(getRssUrl());
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        m_feedList.getAdapter().notifyDataSetChanged();
    }

    private void initFeedList() {
        setLayoutManagerByDeviceType();
        RssItemAdapter adapter = new RssItemAdapter(RssReaderApp.getRssItemManager().getAll(true));
        adapter.setOnItemClickListener(item -> m_rssItemInfoPresenter.onRssItemClick(item.getId()));
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
    public void showErrorRefreshingMessage(int messageId) {
        Toast.makeText(this, messageId, Toast.LENGTH_LONG).show();
    }

    @Click(R.id.view_button)
    void onViewButtonClick() {
        WebViewActivity_.intent(this).extra("link", m_itemLink).start();
        m_itemInfoDrawer.closeDrawers();
    }

    public boolean ensureNetworkConnection() {
        if (NetUtils.isNetworkConnected(this)) {
            return true;
        }
        Toast.makeText(this, R.string.internet_connection_error, Toast.LENGTH_LONG).show();
        return false;
    }

    public String getRssUrl() {
        SharedPreferences settingsPrefs = getSharedPreferences(SettingsActivity.SETTINGS_PREFS, MODE_PRIVATE);
        return settingsPrefs.getString(SettingsActivity.URL_KEY, "");
    }

    private void setLayoutManagerByDeviceType() {
        if (isTablet()) {
            m_feedList.setHasFixedSize(true);
            m_feedList.setLayoutManager(new GridLayoutManager(
                    this,
                    ViewUtils.getSpanCount(this, R.dimen.rss_item_table_view_width)));

        } else {
            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
            DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(
                    m_feedList.getContext(),
                    linearLayoutManager.getOrientation());
            m_feedList.setLayoutManager(linearLayoutManager);
            m_feedList.addItemDecoration(dividerItemDecoration);
        }
    }

    private boolean isTablet() {
        return getResources().getBoolean(R.bool.isTablet);
    }

    @Override
    public void showRssItemInfo(Long rssItemId) {
        if (isTablet()) {
            RssItem rssItem = RssReaderApp.getRssItemManager().getRssItemById(rssItemId);
            m_thumbnail.setImageURI(rssItem.getImageUrl());
            m_drawerTitle.setText(rssItem.getTitle());
            m_drawerDescription.setText(rssItem.getDescription());
            m_drawerAuthor.setText(rssItem.getAutor());
            m_itemLink = rssItem.getLink();
            m_drawerDate.setText(DateUtils.asRegionalDateString(rssItem.getPubDate(), this));
            m_itemInfoDrawer.openDrawer(Gravity.RIGHT);
        } else {
            RssItemInfoActivity_.intent(this).extra("rssItemId", rssItemId).start();
        }
    }
}
