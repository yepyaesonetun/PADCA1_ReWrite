package com.primeyz.padca1_rewrite.activities;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v7.widget.Toolbar;
import android.view.Menu;

import com.ashokvarma.bottomnavigation.BottomNavigationBar;
import com.ashokvarma.bottomnavigation.BottomNavigationItem;
import com.primeyz.padca1_rewrite.R;
import com.primeyz.padca1_rewrite.data.model.SeriesModal;
import com.primeyz.padca1_rewrite.data.vo.BaseVO;
import com.primeyz.padca1_rewrite.data.vo.CategoryVO;
import com.primeyz.padca1_rewrite.data.vo.CurrentProgramVO;
import com.primeyz.padca1_rewrite.data.vo.TopicVO;
import com.primeyz.padca1_rewrite.delegates.ProgramDelegate;
import com.primeyz.padca1_rewrite.events.RestApiEvent;
import com.primeyz.padca1_rewrite.fragments.EmptyFragment;
import com.primeyz.padca1_rewrite.fragments.MeFragment;
import com.primeyz.padca1_rewrite.fragments.SeriesFragment;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import io.reactivex.subjects.PublishSubject;

public class MainActivity extends BaseActivity implements BottomNavigationBar.OnTabSelectedListener,ProgramDelegate{

    public static final String TAG_MEDITATE = "Meditation";
    public static final String TAG_ME = "Me";
    public static final String TAG_MORE = "More";

    private ArrayList<String> fragmentTags;
    private Handler mHandler;

    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @BindView(R.id.activity_main_bn)
    BottomNavigationBar bottomNavigationBar;


//        TabLayout tabLayout = findViewById(R.id.sliding_tabs);
//        ViewPager viewPager = findViewById(R.id.view_pager);
//
//        SimpleFragmentPagerAdapter pagerAdapter = new SimpleFragmentPagerAdapter(this, getSupportFragmentManager());
//        viewPager.setAdapter(pagerAdapter);
//        tabLayout.setupWithViewPager(viewPager);

    @Override
    protected int getLayoutRes() {
        return R.layout.activity_main;
    }

    @Override
    protected void setUpContents(Bundle savedInstanceState) {
        setSupportActionBar(toolbar);
        init();
        setupBottomNavBar();
        initLoad();

    }

    private void init() {
        mHandler = new Handler();
        fragmentTags = new ArrayList<>();
    }

    private void initLoad() {
        setUpAndLoadFragment(TAG_MEDITATE);
        bottomNavigationBar.selectTab(0, false);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main,menu);
        return true;
    }

    private void setupBottomNavBar() {
        BottomNavigationItem navHome = new BottomNavigationItem(R.drawable.ic_nav_home_icon, "Meditate");

        BottomNavigationItem navWishList = new BottomNavigationItem(R.drawable.ic_nav_person_24dp, "Me");

        BottomNavigationItem navMyCart = new BottomNavigationItem(R.drawable.ic_nav_more_icon, "More");

        bottomNavigationBar.setTabSelectedListener(this);

        bottomNavigationBar.addItem(navHome);
        fragmentTags.add(TAG_MEDITATE);

        bottomNavigationBar.addItem(navWishList);
        fragmentTags.add(TAG_ME);

        bottomNavigationBar.addItem(navMyCart);
        fragmentTags.add(TAG_MORE);

        bottomNavigationBar.initialise();
    }

    private void replaceFragment(Fragment fragment) {
        mHandler.post(() -> getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.activity_main_fragmentContainer, fragment)
                .commitAllowingStateLoss());
    }

    private String getFragmentTag(int position) {
        if (position >= 0 && position < fragmentTags.size()) {
            return fragmentTags.get(position);
        } else {
            return TAG_MEDITATE;
        }
    }

    private void setUpAndLoadFragment(String tag) {
        switch (tag) {
            case TAG_MEDITATE:
                replaceFragment(new SeriesFragment());
                break;
            case TAG_ME:
                replaceFragment(new MeFragment());
                break;
            case TAG_MORE:
                replaceFragment(new EmptyFragment());
                break;
            default:
                replaceFragment(new SeriesFragment());

        }
    }

    @Override
    public void onTabSelected(int position) {
        mHandler.postDelayed(() -> {
            String tag = getFragmentTag(position);
            setUpAndLoadFragment(tag);
        }, 300);
    }

    @Override
    public void onTabUnselected(int position) {
        // Do Nothing yet!
    }

    @Override
    public void onTabReselected(int position) {
        // Do Nothing yet!
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onErrorInvokingAPI(RestApiEvent.ErrorInvokingAPIEvent event) {
        Snackbar.make(bottomNavigationBar, event.getErrorMsg(), Snackbar.LENGTH_INDEFINITE).show();
    }

    @Override
    public void onTapCurrent() {
        Intent intent = ProgramDetailActivity.newIntent(this,"CURRENT_PROGRAM");
        startActivity(intent);
    }

    @Override
    public void onTapProgram(String id) {
        Intent intent = ProgramDetailActivity.newIntent(this, "CATEGORY",id);
        startActivity(intent);
    }
}
