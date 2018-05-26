package com.primeyz.padca1_rewrite.viewholders;

import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.primeyz.padca1_rewrite.R;
import com.primeyz.padca1_rewrite.data.vo.BaseVO;

public class SeriesViewHolder extends BaseViewHolder<BaseVO>{

    public RecyclerView rvTopic;

    public SeriesViewHolder(View itemView) {
        super(itemView);
        rvTopic = itemView.findViewById(R.id.rv_topic);
    }

    @Override
    public void setData(BaseVO data) {

    }

    @Override
    public void onClick(View v) {

    }
}
