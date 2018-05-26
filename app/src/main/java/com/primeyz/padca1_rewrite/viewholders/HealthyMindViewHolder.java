package com.primeyz.padca1_rewrite.viewholders;

import android.view.View;
import android.widget.TextView;

import com.primeyz.padca1_rewrite.R;
import com.primeyz.padca1_rewrite.data.vo.ProgramVO;

import butterknife.BindView;

public class HealthyMindViewHolder extends BaseViewHolder {

    @BindView(R.id.tv_item_title)
    TextView tvEveningTitle;

    @BindView(R.id.tv_item_minute)
    TextView tvEveningDuration;

    public HealthyMindViewHolder(View itemView) {
        super(itemView);
    }

    @Override
    public void setData(Object data) {
        ProgramVO newHabitDataObj = (ProgramVO) data;
        if (newHabitDataObj != null) {
            tvEveningTitle.setText(newHabitDataObj.getTitle());
            tvEveningDuration.setText(newHabitDataObj.getAverageLengths().get(0) + " mins");
        }
    }

    @Override
    public void onClick(View view) {

    }
}
