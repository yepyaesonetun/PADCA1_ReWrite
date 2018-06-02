package com.primeyz.padca1_rewrite.viewholders;

import android.support.v7.widget.RecyclerView;
import android.view.View;

import butterknife.ButterKnife;

/**
 * Created by yepyaesonetun on 5/26/18.
 **/

public abstract class BaseViewHolder<W> extends RecyclerView.ViewHolder implements View.OnClickListener {

    public W mData;
    private int position;

    public BaseViewHolder(View itemView) {
        super(itemView);

        itemView.setOnClickListener(this);
    }

    public abstract void setData(W data, int position);
}
