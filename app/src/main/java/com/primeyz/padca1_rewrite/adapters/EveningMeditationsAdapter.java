package com.primeyz.padca1_rewrite.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import com.primeyz.padca1_rewrite.R;
import com.primeyz.padca1_rewrite.data.vo.ProgramVO;
import com.primeyz.padca1_rewrite.viewholders.BaseViewHolder;
import com.primeyz.padca1_rewrite.viewholders.EveningMeditationViewHolder;

import butterknife.ButterKnife;


public class EveningMeditationsAdapter extends BaseViewHolder<ProgramVO> {


    public EveningMeditationsAdapter(View itemView) {
        super(itemView);
        ButterKnife.bind(this,itemView);
    }

    @Override
    public void setData(ProgramVO data) {

    }

    @Override
    public void onClick(View v) {

    }
}
