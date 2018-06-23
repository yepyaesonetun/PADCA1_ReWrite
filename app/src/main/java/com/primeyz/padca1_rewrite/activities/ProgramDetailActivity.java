package com.primeyz.padca1_rewrite.activities;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.Snackbar;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.primeyz.padca1_rewrite.R;
import com.primeyz.padca1_rewrite.adapters.SessionsRVAdapter;
import com.primeyz.padca1_rewrite.data.model.SeriesModal;
import com.primeyz.padca1_rewrite.data.vo.CurrentProgramVO;
import com.primeyz.padca1_rewrite.data.vo.ProgramVO;
import com.primeyz.padca1_rewrite.events.RestApiEvent;
import com.primeyz.padca1_rewrite.mvp.presenters.ProgramDetailPresenter;
import com.primeyz.padca1_rewrite.mvp.views.ProgramDetailView;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ProgramDetailActivity extends AppCompatActivity implements ProgramDetailView {

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

    private ProgramDetailPresenter mPresenter;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pro_detail);
        ButterKnife.bind(this, this);
        setUpContents(savedInstanceState);
    }

    // using static factory pattern : Good Practice
    public static Intent newIntent(Context context, String type, String id) {
        Intent intent = new Intent(context, ProgramDetailActivity.class);
        intent.putExtra("CATEGORY", type);
        intent.putExtra("category_id", id);
        return intent;
    }

    // for currentProgram
    public static Intent newIntent(Context context, String type) {
        Intent intent = new Intent(context, ProgramDetailActivity.class);
        intent.putExtra("CATEGORY", type);
        return intent;
    }


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

        mPresenter = new ProgramDetailPresenter(this);
        mPresenter.onFinishUISetUp(programData, categoryId);

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_program, menu);
        return true;
    }


    @OnClick(R.id.fab)
    public void onClick(View view) {
        Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                .setAction("Action", null).show();
    }

    @Override
    public void displayErrorMsg(String errorMsg) {
        Toast.makeText(this, errorMsg, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void displayCurrentProgramData(CurrentProgramVO currentProgramVO) {
        setUpCurrentData(currentProgramVO);
    }

    @Override
    public void displayProgramData(ProgramVO programVO) {
        setUpProgramData(programVO);
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
}
