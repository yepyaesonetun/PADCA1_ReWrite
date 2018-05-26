package com.primeyz.padca1_rewrite.components;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.primeyz.padca1_rewrite.R;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by yepyaesonetun on 5/26/18.
 **/

public class ProgressViewPod extends LinearLayout {

    @BindView(R.id.tv_progress_current_day_streak)
    TextView tvCurrentDayStreak;

    @BindView(R.id.tv_progress_total_session)
    TextView tvTotalSessions;

    @BindView(R.id.tv_progress_total_minutes)
    TextView tvTotalMinutes;

    public ProgressViewPod(Context context) {
        super(context);
    }

    public ProgressViewPod(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public ProgressViewPod(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        ButterKnife.bind(this, this);
    }

    public void setTvCurrentDayStreak(int value){
        tvCurrentDayStreak.setText(String.valueOf(value));
    }

    public void setTvTotalSessions(int value){
        tvTotalSessions.setText(String.valueOf(value));
    }

    public void setTvTotalMinutes(int value){
        tvTotalMinutes.setText(String.valueOf(value));
    }
}
