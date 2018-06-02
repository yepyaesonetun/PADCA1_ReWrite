package com.primeyz.padca1_rewrite.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.primeyz.padca1_rewrite.R;
import com.primeyz.padca1_rewrite.viewholders.SessionsViewHolder;

/**
 * Created by yepyaesonetun on 6/2/18.
 **/

public class SessionsRVAdapter extends BaseRecyclerAdapter {

    public SessionsRVAdapter(Context context) {
        super(context);
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_view_session, parent, false);
        return new SessionsViewHolder(view);
    }

}
