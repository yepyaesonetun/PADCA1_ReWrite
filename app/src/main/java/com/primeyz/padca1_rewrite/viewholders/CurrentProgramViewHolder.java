package com.primeyz.padca1_rewrite.viewholders;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.CardView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.primeyz.padca1_rewrite.R;
import com.primeyz.padca1_rewrite.activities.ProgramDetailActivity;
import com.primeyz.padca1_rewrite.data.vo.CurrentProgramVO;
import com.primeyz.padca1_rewrite.delegates.ProgramDelegate;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by yepyaesonetun on 5/26/18.
 **/

public class CurrentProgramViewHolder extends BaseViewHolder<CurrentProgramVO> {

    @BindView(R.id.cv_current_program)
    CardView cvCurrentProgram;

    @BindView(R.id.img_item_bg)
    ImageView imgCurrentBg;

    @BindView(R.id.textView_current_period)
    TextView tvCurrentPeriod;

    @BindView(R.id.tv_item_minute)
    TextView tvAverageLenth;

    private ProgramDelegate mProgramDelegate;
    private CurrentProgramVO currentProgramVO;
    private Context mContext;

    public CurrentProgramViewHolder(View itemView, ProgramDelegate delegate) {
        super(itemView);
        mContext = itemView.getContext();
        this.mProgramDelegate = delegate;
        ButterKnife.bind(this, itemView);
    }

    @SuppressLint("DefaultLocale")
    @Override
    public void setData(CurrentProgramVO data, int position) {
        mData = data;
        currentProgramVO = data;
        Glide.with(itemView.getContext()).load(R.drawable.sample).into(imgCurrentBg);
        tvCurrentPeriod.setText(data.getCurrentPeriod());
        tvAverageLenth.setText(String.format("%d mins", data.getAverageLengths()[0]));
    }

    @Override
    public void onClick(View v) {
        mProgramDelegate.onTapCurrent();
    }
}
