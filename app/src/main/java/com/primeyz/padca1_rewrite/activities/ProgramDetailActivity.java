package com.primeyz.padca1_rewrite.activities;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.Snackbar;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.View;
import android.widget.TextView;

import com.primeyz.padca1_rewrite.R;
import com.primeyz.padca1_rewrite.adapters.SessionsRVAdapter;
import com.primeyz.padca1_rewrite.data.model.SeriesModal;
import com.primeyz.padca1_rewrite.data.vo.CurrentProgramVO;
import com.primeyz.padca1_rewrite.data.vo.ProgramVO;
import com.primeyz.padca1_rewrite.events.RestApiEvent;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import butterknife.BindView;
import butterknife.OnClick;

public class ProgramDetailActivity extends BaseActivity {

    @BindView(R.id.nested_scrollView)
    NestedScrollView nestedScrollView;

    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @BindView(R.id.toolbar_layout)
    CollapsingToolbarLayout collapsingToolbarLayout;

    @BindView(R.id.textView_program_title)
    TextView textView_program_title;

    @BindView(R.id.rv_all_sessions)
    RecyclerView rvSessions;

    private SessionsRVAdapter allSessionsAdapter;

    @Override
    protected int getLayoutRes() {
        return R.layout.activity_pro_detail;
    }

    @Override
    protected void setUpContents(Bundle savedInstanceState) {
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        collapsingToolbarLayout.setTitleEnabled(true);
        toolbar.setNavigationOnClickListener(v -> finish());

        rvSessions.setLayoutManager(new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.VERTICAL, false));
        allSessionsAdapter = new SessionsRVAdapter(this);
        rvSessions.setAdapter(allSessionsAdapter);

        String programData = getIntent().getStringExtra("CATEGORY");
        String categoryId = getIntent().getStringExtra("category_id");

        if(programData.equalsIgnoreCase("CURRENT_PROGRAM")){
            CurrentProgramVO currentProgramVO = SeriesModal.getObjInstance().mCurrentProgramVO;
            setUpCurrentData(currentProgramVO);
        }else if(programData.equalsIgnoreCase("CATEGORY")){
            ProgramVO programVO = SeriesModal.getObjInstance().getProgramId(categoryId);
            setUpProgramData(programVO);
        }

    }

    private void setUpProgramData(ProgramVO programVO) {
        collapsingToolbarLayout.setTitle(programVO.getTitle());
        textView_program_title.setText(programVO.getDescription());
        allSessionsAdapter.appendNewData(programVO.getSessions());
    }

    private void setUpCurrentData(CurrentProgramVO currentProgramVO) {
        collapsingToolbarLayout.setTitle(currentProgramVO.getTitle());
        textView_program_title.setText(currentProgramVO.getDescription());
        allSessionsAdapter.appendNewData(currentProgramVO.getSessionVOList());
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_program,menu);
        return true;
    }


    public static Intent newIntent(Context context) {
        return new Intent(context, ProgramDetailActivity.class);
    }


    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onErrorInvokingAPI(RestApiEvent.ErrorInvokingAPIEvent event) {
        Snackbar.make(nestedScrollView, event.getErrorMsg(), Snackbar.LENGTH_INDEFINITE).show();
    }

    @OnClick(R.id.fab)
    public void onClick(View view){
        Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                .setAction("Action", null).show();
    }
}
