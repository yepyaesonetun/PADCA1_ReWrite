package com.primeyz.padca1_rewrite.viewholders;

import android.annotation.SuppressLint;
import android.view.View;
import android.widget.TextView;

import com.primeyz.padca1_rewrite.R;
import com.primeyz.padca1_rewrite.data.vo.SessionVO;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by yepyaesonetun on 6/2/18.
 **/

public class SessionsViewHolder extends BaseViewHolder {

    @BindView(R.id.tv_s_count)
    TextView tvSessionCount;

    @BindView(R.id.tv_s_title)
    TextView tvSessionTitle;

    @BindView(R.id.tv_s_duration)
    TextView tvSessionDuration;

    public SessionsViewHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }


    @SuppressLint("SetTextI18n")
    @Override
    public void setData(Object data, int position) {
        SessionVO sessionsVO = (SessionVO) data;
            tvSessionCount.setText("" + (position + 1));
        tvSessionTitle.setText(sessionsVO.getTitle());
        tvSessionDuration.setText(secondsToString(sessionsVO.getLengthInSeconds()));
    }

    @Override
    public void onClick(View v) {

    }

    @SuppressLint("DefaultLocale")
    private String secondsToString(int timeStamp) {
        return String.format("%02d:%02d", timeStamp / 60, timeStamp % 60);
    }
}
