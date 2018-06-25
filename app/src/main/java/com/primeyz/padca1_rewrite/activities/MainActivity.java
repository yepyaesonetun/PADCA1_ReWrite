package com.primeyz.padca1_rewrite.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.primeyz.padca1_rewrite.R;
import com.primeyz.padca1_rewrite.adapters.MyFragmentPagerAdapter;
import com.primeyz.padca1_rewrite.data.vo.BaseVO;
import com.primeyz.padca1_rewrite.delegates.HomePresenterDelegate;
import com.primeyz.padca1_rewrite.events.RestApiEvent;
import com.primeyz.padca1_rewrite.fragments.EmptyFragment;
import com.primeyz.padca1_rewrite.fragments.MeFragment;
import com.primeyz.padca1_rewrite.fragments.SeriesFragment;
import com.primeyz.padca1_rewrite.mvp.presenters.SeriesListPresenter;
import com.primeyz.padca1_rewrite.mvp.views.SeriesListView;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.List;

import butterknife.BindView;

public class MainActivity extends BaseActivity
        implements HomePresenterDelegate,
        SeriesListView {

    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @BindView(R.id.view_pager)
    ViewPager view_pager;

    @BindView(R.id.navigation)
    BottomNavigationView navigation;

    private SeriesListPresenter mPresenter;
    private MyFragmentPagerAdapter myPagerAdapter;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    view_pager.setCurrentItem(0);
                    return true;
                case R.id.navigation_notifications:
                    view_pager.setCurrentItem(1);
                    return true;
                case R.id.navigation_menu:
                    view_pager.setCurrentItem(2);
                    return true;
            }
            return false;
        }

    };


    @Override
    protected int getLayoutRes() {
        return R.layout.activity_main;
    }

    @Override
    protected void setUpContents(Bundle savedInstanceState) {
        setSupportActionBar(toolbar);
        init();
        setupBottomNavBar();

    }

    private void init() {

        mPresenter = new SeriesListPresenter(this);
        mPresenter.onCreate();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    private void setupBottomNavBar() {
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        view_pager.setOffscreenPageLimit(3);

        myPagerAdapter = new MyFragmentPagerAdapter(getSupportFragmentManager());
        myPagerAdapter.addFragment(new SeriesFragment(), "Series");
        myPagerAdapter.addFragment(new MeFragment(), "Me");
        myPagerAdapter.addFragment(new EmptyFragment(), "More");
        view_pager.setAdapter(myPagerAdapter);

        view_pager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            }

            @Override
            public void onPageSelected(int position) {
                if (position == 0) {
                    navigation.getMenu().getItem(0).setChecked(true);
//                    setupToolbarText(getString(R.string.app_name));
                } else if (position == 1) {
                    navigation.getMenu().getItem(1).setChecked(true);
                } else if (position == 2) {
                    navigation.getMenu().getItem(2).setChecked(true);
                }

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }


    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onErrorInvokingAPI(RestApiEvent.ErrorInvokingAPIEvent event) {
        Snackbar.make(navigation, event.getErrorMsg(), Snackbar.LENGTH_INDEFINITE).show();
    }

    @Override
    public SeriesListPresenter getPresenter() {
        return mPresenter;
    }

    @Override
    public void onStart() {
        super.onStart();
        mPresenter.onStart();
    }

    @Override
    public void onStop() {
        super.onStop();
        mPresenter.onStop();
    }

    @Override
    public void displayErrorMsg(String errorMsg) {
        Toast.makeText(this, errorMsg, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void displayData(List<BaseVO> baseVOList) {
        SeriesFragment seriesFragment = (SeriesFragment) myPagerAdapter.getItem(0);
        seriesFragment.addDataToAdapter(baseVOList);
    }

    @Override
    public void launchCurrentProgram() {
        Intent intent = ProgramDetailActivity.newIntent(this, "CURRENT_PROGRAM");
        startActivity(intent);
    }

    @Override
    public void launchProgram(String id) {
        Intent intent = ProgramDetailActivity.newIntent(this, "CATEGORY", id);
        startActivity(intent);
    }
}
