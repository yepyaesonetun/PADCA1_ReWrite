package com.primeyz.padca1_rewrite.viewholders;

import android.view.View;
import android.widget.TextView;

import com.primeyz.padca1_rewrite.R;
import com.primeyz.padca1_rewrite.data.vo.ProgramVO;

import butterknife.BindView;
import butterknife.ButterKnife;

public class EveningMeditationViewHolder extends BaseViewHolder {

    @BindView(R.id.tv_item_title)
    TextView tvEveningTitle;

    @BindView(R.id.tv_item_minute)
    TextView tvEveningDuration;

    public EveningMeditationViewHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }

    @Override
    public void setData(Object data) {
        ProgramVO eveningDatObj = (ProgramVO) data;
        if (eveningDatObj != null) {
            tvEveningTitle.setText(eveningDatObj.getTitle());
            tvEveningDuration.setText(eveningDatObj.getAverageLengths().get(0) + " mins");
        }
    }

    @Override
    public void onClick(View view) {

    }
}
