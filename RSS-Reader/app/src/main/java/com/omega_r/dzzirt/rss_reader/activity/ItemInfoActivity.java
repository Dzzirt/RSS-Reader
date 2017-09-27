package com.omega_r.dzzirt.rss_reader.activity;

import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.TextView;

import com.app.dzzirt.rss_reader.R;
import com.omega_r.dzzirt.rss_reader.viewmodel.FeedItemInfoViewModel;
import com.omega_r.dzzirt.rss_reader.utils.DateUtils;
import com.facebook.drawee.view.SimpleDraweeView;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by Dzzirt on 07.01.2017.
 */
public class ItemInfoActivity extends BaseActivity {

    private static final long NO_ID = 0;

    private static final String EXTRA_ITEM_ID = "itemId";

    @BindView(R.id.simpledraweeview_thumbnail)
    SimpleDraweeView mThumbnail;

    @BindView(R.id.textview_title)
    TextView mTitleTextView;

    @BindView(R.id.textview_description)
    TextView mDesctiptionTextView;

    @BindView(R.id.textview_author)
    TextView mAuthorTextView;

    @BindView(R.id.textview_date)
    TextView mDateTextView;

    private FeedItemInfoViewModel mFeedItemInfoViewModel;

    private String mItemUrl;

    public static Intent createIntent(Context context, long itemId) {
        Intent intent = new Intent(context, ItemInfoActivity.class);
        intent.putExtra(EXTRA_ITEM_ID, itemId);

        return intent;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.screen_info);

        mFeedItemInfoViewModel = ViewModelProviders.of(this).get(FeedItemInfoViewModel.class);

        init();
    }

    @Override
    protected String getToolbarTitle() {
        return getString(R.string.preview);
    }

    void init() {
        mFeedItemInfoViewModel.getItemObservable().observe(this, feedItem -> {
            mItemUrl = feedItem.getUrl();

            mThumbnail.setImageURI(feedItem.getImageUrl());
            mTitleTextView.setText(feedItem.getTitle());
            mDesctiptionTextView.setText(feedItem.getDescription());
            mAuthorTextView.setText(feedItem.getAuthor());
            mDateTextView.setText(DateUtils.asRegionalDateString(feedItem.getDate(), ItemInfoActivity.this));
        });

        long itemId = getIntent().getLongExtra(EXTRA_ITEM_ID, NO_ID);
        mFeedItemInfoViewModel.setItemId(itemId);
    }

    @OnClick(R.id.button_view)
    void onViewButtonClick() {
       startActivity(WebViewActivity.createIntent(this, mItemUrl));
    }

}
