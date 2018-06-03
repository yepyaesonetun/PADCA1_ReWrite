package com.primeyz.padca1_rewrite.viewholders;

import android.view.View;
import android.widget.TextView;

import com.primeyz.padca1_rewrite.R;
import com.primeyz.padca1_rewrite.data.vo.ProgramVO;
import com.primeyz.padca1_rewrite.delegates.ProgramDelegate;

import butterknife.BindView;
import butterknife.ButterKnife;

public class TopicViewHolder extends BaseViewHolder<ProgramVO>{

	@BindView(R.id.tv_item_title)
	TextView tvItemTitle;

	@BindView(R.id.tv_item_minute)
	TextView tvItemMinute;

	ProgramVO programDataObj;
	ProgramDelegate mDelegate;

	public TopicViewHolder(View itemView, ProgramDelegate delegate) {
		super(itemView);
		this.mDelegate = delegate;
		ButterKnife.bind(this, itemView);
	}

	@Override
	public void setData(ProgramVO data, int position) {
		programDataObj = data;
		tvItemTitle.setText(data.getTitle());
		tvItemMinute.setText(String.format("%d mins", data.getAverageLengths().get(0)));
	}

	@Override
	public void onClick(View v) {
		mDelegate.onTapProgram(programDataObj.programId);
	}
}
