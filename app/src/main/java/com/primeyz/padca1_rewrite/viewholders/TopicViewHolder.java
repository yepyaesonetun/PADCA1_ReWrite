package com.primeyz.padca1_rewrite.viewholders;

import android.graphics.drawable.RippleDrawable;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.primeyz.padca1_rewrite.R;
import com.primeyz.padca1_rewrite.data.vo.ProgramVO;

import butterknife.BindView;
import butterknife.ButterKnife;

public class TopicViewHolder extends BaseViewHolder<ProgramVO>{

	@BindView(R.id.tv_item_title)
	TextView tvItemTitle;

	@BindView(R.id.tv_item_minute)
	TextView tvItemMinute;

	public TopicViewHolder(View itemView) {
		super(itemView);
		ButterKnife.bind(this, itemView);
	}

	@Override
	public void setData(ProgramVO data) {
		tvItemTitle.setText(data.getTitle());
		tvItemMinute.setText(String.format("%d mins", data.getAverageLengths().get(0)));
	}

	@Override
	public void onClick(View v) {

	}
}
