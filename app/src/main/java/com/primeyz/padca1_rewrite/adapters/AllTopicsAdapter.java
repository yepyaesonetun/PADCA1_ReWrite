package com.primeyz.padca1_rewrite.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import com.primeyz.padca1_rewrite.R;
import com.primeyz.padca1_rewrite.viewholders.AllTopicsViewHolder;

public class AllTopicsAdapter extends BaseRecyclerAdapter {

    public AllTopicsAdapter(Context context) {
        super(context);
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View allTopicsItemView = mLayoutInflator.inflate(R.layout.item_view_topic, parent, false);
        return new AllTopicsViewHolder(allTopicsItemView);
    }
}
