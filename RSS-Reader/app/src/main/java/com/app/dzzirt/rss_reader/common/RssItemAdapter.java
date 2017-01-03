package com.app.dzzirt.rss_reader.common;

import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.app.dzzirt.rss_reader.R;
import com.facebook.drawee.view.SimpleDraweeView;

import java.util.List;

/**
 * Created by Dzzirt on 01.01.2017.
 */

public class RssItemAdapter extends RecyclerView.Adapter<RssItemAdapter.RssItemViewHolder> {

    private List<RSSItem> m_items;
    private OnItemClickListener m_onItemClickListener;

    public RssItemAdapter(List<RSSItem> items) {
        m_items = items;
    }

    class RssItemViewHolder extends RecyclerView.ViewHolder {

        SimpleDraweeView thumbnail;
        TextView title;
        TextView describtion;
        View view;

        RssItemViewHolder(View itemView) {
            super(itemView);
            view = itemView;
            thumbnail = (SimpleDraweeView) itemView.findViewById(R.id.thumbnail);
            title = (TextView) itemView.findViewById(R.id.title);
            describtion = (TextView) itemView.findViewById(R.id.describtion);
        }

    }
    
    public void setOnItemClickListener(OnItemClickListener listener) {
        m_onItemClickListener = listener;
    }

    public void clear() {
        m_items.clear();
        notifyDataSetChanged();
    }

    public void addAll(List<RSSItem> list) {
        m_items.addAll(list);
        notifyDataSetChanged();
    }

    public RssItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.rss_element, parent, false);
        return new RssItemViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RssItemViewHolder holder, int position) {
        final RSSItem rssItem = m_items.get(position);
        if (m_onItemClickListener != null) {
            holder.view.setOnClickListener(view -> m_onItemClickListener.onItemClick(rssItem));
        }
        holder.title.setText(rssItem.getTitle());
        holder.describtion.setText(rssItem.getDescribtion());
        holder.thumbnail.setImageURI(Uri.parse(rssItem.getImagePath()));
    }

    @Override
    public int getItemCount() {
        return m_items.size();
    }
}
