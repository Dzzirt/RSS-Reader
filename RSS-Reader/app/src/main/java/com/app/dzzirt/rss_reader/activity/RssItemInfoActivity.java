package com.app.dzzirt.rss_reader.activity;

import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.TextView;

import com.app.dzzirt.rss_reader.R;
import com.app.dzzirt.rss_reader.greendao.RssItem;
import com.app.dzzirt.rss_reader.utils.DateUtils;
import com.facebook.drawee.view.SimpleDraweeView;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.Extra;
import org.androidannotations.annotations.ViewById;
import org.jsoup.helper.DataUtil;

import java.text.ParseException;

/**
 * Created by Dzzirt on 07.01.2017.
 */
@EActivity(R.layout.rss_item_info_activity)
public class RssItemInfoActivity extends AppCompatActivity {

    @Extra
    Long rssItemId;

    @ViewById(R.id.info_toolbar)
    Toolbar m_toolbar;

    @ViewById(R.id.info_thumbnail)
    SimpleDraweeView m_thumbnail;

    @ViewById(R.id.info_title)
    TextView m_title;

    @ViewById(R.id.info_description)
    TextView m_description;

    @ViewById(R.id.info_author)
    TextView m_author;

    @ViewById(R.id.info_date)
    TextView m_date;

    @AfterViews
    void init() {
        initToolbar();
        RssItem rssItem = RssReaderApp.getRssItemManager().getRssItemById(rssItemId);
        m_thumbnail.setImageURI(rssItem.getImageUrl());
        m_title.setText(rssItem.getTitle());
        m_description.setText(rssItem.getDescription());
        m_author.setText(rssItem.getAutor());
        try {
            m_date.setText(
                    DateUtils.formatToRegionalDate(
                            DateUtils.parseRssDate(rssItem.getPubDate()), this));
        } catch (ParseException ignored) {}
    }

    @Click(R.id.view_button)
    void onViewButtonClick() {
        // show webview activity
    }

    private void initToolbar() {
        setSupportActionBar(m_toolbar);
        m_toolbar.setNavigationOnClickListener(v -> onBackPressed());
    }
}
