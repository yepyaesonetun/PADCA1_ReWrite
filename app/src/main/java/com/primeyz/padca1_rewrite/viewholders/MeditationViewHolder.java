package com.primeyz.padca1_rewrite.viewholders;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.primeyz.padca1_rewrite.R;
import com.primeyz.padca1_rewrite.adapters.MeditationAdapter;
import com.primeyz.padca1_rewrite.data.vo.CategoryVO;
import com.primeyz.padca1_rewrite.delegates.ProgramDelegate;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by yepyaesonetun on 5/26/18.
 **/

public class MeditationViewHolder extends BaseViewHolder<CategoryVO> {

    @BindView(R.id.meditationview_recycler)
    RecyclerView meditationRecycler;
    @BindView(R.id.meditation_title)
    TextView meditationTitle;

    private ProgramDelegate mDelegate;


    public MeditationViewHolder(View itemView, ProgramDelegate delegate) {
        super(itemView);
        this.mDelegate = delegate;
        ButterKnife.bind(this, itemView);
    }

    @Override
    public void setData(CategoryVO data, int position) {
        meditationTitle.setText(data.getTitle());

        MeditationAdapter meditationItemAdapter=new MeditationAdapter(itemView.getContext(), mDelegate);

        meditationItemAdapter.setNewData(data.getPrograms());
        LinearLayoutManager layoutManager = new LinearLayoutManager(itemView.getContext(), LinearLayoutManager.HORIZONTAL, false);
        meditationRecycler.setLayoutManager(layoutManager);
        meditationRecycler.setAdapter(meditationItemAdapter);
    }

    @Override
    public void onClick(View v) {

    }
}
