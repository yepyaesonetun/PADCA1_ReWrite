package com.primeyz.padca1_rewrite.viewholders;

import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.primeyz.padca1_rewrite.R;

public class SeriesViewHolder extends RecyclerView.ViewHolder {

    public RecyclerView rvTopic;

    public SeriesViewHolder(View itemView) {
        super(itemView);
        rvTopic = itemView.findViewById(R.id.rv_topic);
    }
}
