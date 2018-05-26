package com.primeyz.padca1_rewrite.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.primeyz.padca1_rewrite.R;
import com.primeyz.padca1_rewrite.data.vo.BaseVO;
import com.primeyz.padca1_rewrite.data.vo.CategoryVO;
import com.primeyz.padca1_rewrite.data.vo.CurrentProgramVO;
import com.primeyz.padca1_rewrite.data.vo.TopicVO;
import com.primeyz.padca1_rewrite.viewholders.AllTopicsViewHolder;
import com.primeyz.padca1_rewrite.viewholders.BaseViewHolder;
import com.primeyz.padca1_rewrite.viewholders.CurrentProgramViewHolder;
import com.primeyz.padca1_rewrite.viewholders.MeditationViewHolder;
import com.primeyz.padca1_rewrite.viewholders.SeriesViewHolder;


public class SeriesRVAdapter extends BaseRecyclerAdapter<BaseViewHolder,BaseVO> {

    private final static int VT_HEADER = 0;
    private final static int VT_SERIES = 1;
    private final static int VT_TOPIC = 2;

    private LayoutInflater mLayoutInflater;
    private Context mContext;

    public SeriesRVAdapter(Context context) {
        super(context);
    }


    @NonNull
    @Override
    public BaseViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        switch (viewType) {
            case VT_HEADER:
                return new CurrentProgramViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.current_program, parent,false));
            case VT_SERIES:
                return new MeditationViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_item_meditation, parent,false));
            case VT_TOPIC:
                return new AllTopicsViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_view_topic, parent,false));
        }
        return new CurrentProgramViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.current_program, parent,false));
    }

    @Override
    public int getItemViewType(int position) {

//        // playing with position
//        if ((position > 1 && position / 2 == 3)) {
//            position = 1;
//        }
//        // playing with position

        if(mData.get(position)instanceof CurrentProgramVO){
            return VT_HEADER;
        }else if (mData.get(position) instanceof CategoryVO){
            return VT_SERIES;
        }else if(mData.get(position) instanceof TopicVO){
            return VT_TOPIC;
        }else {
            return VT_HEADER;
        }

//        switch (position) {
//            case 0:
//                return VT_HEADER;
//            case 1:
//                return VT_SERIES;
//            case 2:
//                return VT_TOPIC;
//            default:
//                return VT_TOPIC;
//        }
    }

//    @Override
//    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
//
//        SeriesViewHolder seriesViewHolder;
//        if (holder.getItemViewType() == 1) {
//            seriesViewHolder = (SeriesViewHolder) holder;
//            seriesViewHolder.rvTopic.setLayoutManager(new LinearLayoutManager(mContext, LinearLayoutManager.HORIZONTAL, false));
//            ItemCardRVAdapter topicAdapter = new ItemCardRVAdapter(mContext);
//            seriesViewHolder.rvTopic.setAdapter(topicAdapter);
//        }
//    }

    @Override
    public int getItemCount() {
        return mData.size();
    }
}
