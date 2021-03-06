package com.primeyz.padca1_rewrite.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.primeyz.padca1_rewrite.R;
import com.primeyz.padca1_rewrite.data.vo.ProgramVO;
import com.primeyz.padca1_rewrite.delegates.ProgramDelegate;
import com.primeyz.padca1_rewrite.viewholders.TopicViewHolder;

/**
 * Created by yepyaesonetun on 5/26/18.
 **/

public class MeditationAdapter extends BaseRecyclerAdapter<TopicViewHolder, ProgramVO> {

    LayoutInflater mLayoutInfalter;
    ProgramDelegate mDelegate;
    public MeditationAdapter(Context context, ProgramDelegate delegate) {
        super(context);
        this.mDelegate = delegate;
        this.mLayoutInfalter = LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public TopicViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = mLayoutInfalter.inflate(R.layout.item_view_card, parent, false);
        return new TopicViewHolder(view,mDelegate);
    }
}
