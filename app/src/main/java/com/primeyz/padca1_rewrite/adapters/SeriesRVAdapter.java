package com.primeyz.padca1_rewrite.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.primeyz.padca1_rewrite.R;
import com.primeyz.padca1_rewrite.data.vo.BaseVO;
import com.primeyz.padca1_rewrite.data.vo.CategoryVO;
import com.primeyz.padca1_rewrite.data.vo.CurrentProgramVO;
import com.primeyz.padca1_rewrite.data.vo.TopicVO;
import com.primeyz.padca1_rewrite.delegates.ProgramDelegate;
import com.primeyz.padca1_rewrite.viewholders.AllTopicsViewHolder;
import com.primeyz.padca1_rewrite.viewholders.BaseViewHolder;
import com.primeyz.padca1_rewrite.viewholders.CurrentProgramViewHolder;
import com.primeyz.padca1_rewrite.viewholders.MeditationViewHolder;


public class SeriesRVAdapter extends BaseRecyclerAdapter<BaseViewHolder, BaseVO> {

    private final static int VT_HEADER = 0;
    private final static int VT_SERIES = 1;
    private final static int VT_TOPIC = 2;

    private ProgramDelegate mDelegate;

//    public SeriesRVAdapter(Context context) {
//        super(context);
//    }

    public SeriesRVAdapter(Context context, ProgramDelegate programDelegate) {
        super(context);
        mDelegate = programDelegate;
    }

    @NonNull
    @Override
    public BaseViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        switch (viewType) {
            case VT_HEADER:
                return new CurrentProgramViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.current_program, parent, false), mDelegate);
            case VT_SERIES:
                return new MeditationViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_item_meditation, parent, false), mDelegate);
            case VT_TOPIC:
                return new AllTopicsViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_view_topic, parent, false));
        }
        return new CurrentProgramViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.current_program, parent, false), mDelegate);
    }

    @Override
    public int getItemViewType(int position) {

//        // playing with position
//        if ((position > 1 && position / 2 == 3)) {
//            position = 1;
//        }
//        // playing with position

        if (mData.get(position) instanceof CurrentProgramVO) {
            return VT_HEADER;
        } else if (mData.get(position) instanceof CategoryVO) {
            return VT_SERIES;
        } else if (mData.get(position) instanceof TopicVO) {
            return VT_TOPIC;
        } else {
            return VT_HEADER;
        }

    }


    @Override
    public int getItemCount() {
        return mData.size();
    }
}
