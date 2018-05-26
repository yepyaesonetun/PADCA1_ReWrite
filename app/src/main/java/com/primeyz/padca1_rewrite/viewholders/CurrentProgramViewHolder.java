package com.primeyz.padca1_rewrite.viewholders;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.primeyz.padca1_rewrite.R;
import com.primeyz.padca1_rewrite.data.vo.CurrentProgramVO;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by yepyaesonetun on 5/26/18.
 **/

public class CurrentProgramViewHolder extends BaseViewHolder<CurrentProgramVO> {

    @BindView(R.id.img_item_bg)
    ImageView imgCurrentBg;

    @BindView(R.id.textView_current_period)
    TextView tvCurrentPeriod;

    @BindView(R.id.tv_item_minute)
    TextView tvAverageLenth;

    public CurrentProgramViewHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this,itemView);
    }

    @Override
    public void setData(CurrentProgramVO data) {
        Glide.with(itemView.getContext()).load(R.drawable.sample).into(imgCurrentBg);
        tvCurrentPeriod.setText(data.getCurrentPeriod());
        tvAverageLenth.setText(String.format("%d mins", data.getAverageLengths().get(0)));
    }

    @Override
    public void onClick(View v) {

    }
}
