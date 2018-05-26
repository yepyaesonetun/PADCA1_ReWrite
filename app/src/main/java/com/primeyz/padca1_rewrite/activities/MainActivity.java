package com.primeyz.padca1_rewrite.activities;

import android.os.Handler;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.ashokvarma.bottomnavigation.BottomNavigationBar;
import com.ashokvarma.bottomnavigation.BottomNavigationItem;
import com.primeyz.padca1_rewrite.R;
import com.primeyz.padca1_rewrite.events.RestApiEvent;
import com.primeyz.padca1_rewrite.fragments.EmptyFragment;
import com.primeyz.padca1_rewrite.fragments.MeFragment;
import com.primeyz.padca1_rewrite.fragments.SeriesFragment;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;

public class MainActivity extends BaseActivity implements BottomNavigationBar.OnTabSelectedListener{

    public static final String TAG_MEDITATE = "Meditation";
    public static final String TAG_ME = "Me";
    public static final String TAG_MORE = "More";

    private BottomNavigationBar bottomNavigationBar;

    private ArrayList<String> fragmentTags;
    private Handler mHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//        TabLayout tabLayout = findViewById(R.id.sliding_tabs);
//        ViewPager viewPager = findViewById(R.id.view_pager);
//
//        SimpleFragmentPagerAdapter pagerAdapter = new SimpleFragmentPagerAdapter(this, getSupportFragmentManager());
//        viewPager.setAdapter(pagerAdapter);
//        tabLayout.setupWithViewPager(viewPager);
        bindViews();
        init();
        setupBottomNavBar();
        initLoad();
    }

    private void init() {
        mHandler = new Handler();
        fragmentTags = new ArrayList<>();
    }

    private void bindViews() {
        bottomNavigationBar = findViewById(R.id.activity_main_bn);
    }

    private void initLoad() {
        setUpAndLoadFragment(TAG_MEDITATE);
        bottomNavigationBar.selectTab(0, false);
    }

    private void setupBottomNavBar() {
        BottomNavigationItem navHome = new BottomNavigationItem(R.drawable.ic_play_arrow_white_24dp, "Meditate");

        BottomNavigationItem navWishList = new BottomNavigationItem(R.drawable.ic_heart, "Me");

        BottomNavigationItem navMyCart = new BottomNavigationItem(R.drawable.ic_flame, "More");

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
}
