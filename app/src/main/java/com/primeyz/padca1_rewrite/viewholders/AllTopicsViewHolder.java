package com.primeyz.padca1_rewrite.viewholders;

import android.view.View;
import android.widget.TextView;

import com.primeyz.padca1_rewrite.R;
import com.primeyz.padca1_rewrite.data.vo.TopicVO;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by eidoshack on 5/17/18.
 */

public class AllTopicsViewHolder extends BaseViewHolder {

    @BindView(R.id.tv_topic_title)
    TextView tvTopicTitle;

    @BindView(R.id.tv_topic_subtitle)
    TextView tvTopicDescription;

    public AllTopicsViewHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }

    @Override
    public void setData(Object data, int position) {
        TopicVO topicDatObj = (TopicVO) data;
        tvTopicTitle.setText(topicDatObj.getTopicName());
        tvTopicDescription.setText(topicDatObj.getTopicDesc());
    }

    @Override
    public void onClick(View view) {

    }
}
