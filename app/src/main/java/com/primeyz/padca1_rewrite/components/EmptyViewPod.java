package com.primeyz.padca1_rewrite.components;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.primeyz.padca1_rewrite.R;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by yepyaesonetun on 5/25/18.
 **/

public class EmptyViewPod extends RelativeLayout {

    @BindView(R.id.iv_empty)
    ImageView ivEmpty;

    @BindView(R.id.tv_empty)
    TextView tvEmpty;

    public EmptyViewPod(Context context) {
        super(context);
    }

    public EmptyViewPod(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public EmptyViewPod(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        ButterKnife.bind(this, this);
    }

    public void setEmptyData(int emptyImageId, String emptyMsg) {
        ivEmpty.setImageResource(emptyImageId);
        tvEmpty.setText(emptyMsg);
    }

    public void setEmptyData(String emptyMsg) {
        tvEmpty.setText(emptyMsg);
    }
}
