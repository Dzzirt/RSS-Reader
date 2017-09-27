package com.omega_r.dzzirt.rss_reader.activity.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.app.dzzirt.rss_reader.R;
import com.omega_r.dzzirt.rss_reader.database.FeedItem;
import com.facebook.drawee.view.SimpleDraweeView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Dzzirt on 01.01.2017.
 */

public class FeedItemAdapter extends RecyclerView.Adapter<FeedItemAdapter.RssItemViewHolder> {

    private List<FeedItem> mItems;
    private OnItemClickListener mItemClickListener;

    public FeedItemAdapter() {
        mItems = new ArrayList<>();
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        mItemClickListener = listener;
    }

    public void setItems(List<FeedItem> items) {
        if (items != null) {
            mItems = items;
        } else {
            mItems.clear();
        }
        notifyDataSetChanged();
    }

    public RssItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_rss, parent, false);
        return new RssItemViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RssItemViewHolder holder, int position) {
        final FeedItem feedItem = mItems.get(position);
        if (mItemClickListener != null) {
            holder.view.setOnClickListener(view -> mItemClickListener.onItemClick(feedItem));
        }
        holder.title.setText(feedItem.getTitle());
        holder.description.setText(feedItem.getDescription());
        holder.thumbnail.setImageURI(feedItem.getImageUrl());
    }

    @Override
    public int getItemCount() {
        return mItems.size();
    }

    class RssItemViewHolder extends RecyclerView.ViewHolder {

        SimpleDraweeView thumbnail;
        TextView title;
        TextView description;
        View view;

        RssItemViewHolder(View itemView) {
            super(itemView);
            view = itemView;
            thumbnail = (SimpleDraweeView) itemView.findViewById(R.id.item_thumbnail);
            title = (TextView) itemView.findViewById(R.id.item_title);
            description = (TextView) itemView.findViewById(R.id.item_description);
        }

    }

    public interface OnItemClickListener {
        void onItemClick(FeedItem element);
    }

}
