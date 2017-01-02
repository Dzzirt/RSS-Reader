package com.app.dzzirt.rss_reader.common;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by Dzzirt on 01.01.2017.
 */

public class RssItemAdapter extends RecyclerView.Adapter<RssItemAdapter.RssItemViewHolder> {


    public class RssItemViewHolder extends RecyclerView.ViewHolder {

        public RssItemViewHolder(View itemView) {
            super(itemView);
        }

    }

    public RssItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(RssItemViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }
}
