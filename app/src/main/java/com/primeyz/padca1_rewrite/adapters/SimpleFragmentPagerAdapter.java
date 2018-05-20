package com.primeyz.padca1_rewrite.adapters;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.primeyz.padca1_rewrite.R;
import com.primeyz.padca1_rewrite.fragments.SeriesFragment;


/**
 * Created by yepyaesonetun on 5/14/18.
 **/

public class SimpleFragmentPagerAdapter extends FragmentPagerAdapter {

    private Context mContext;

    public SimpleFragmentPagerAdapter(Context context, FragmentManager fm) {
        super(fm);
        mContext = context;
    }

    @Override
    public Fragment getItem(int position) {
        if (position == 0) {
            return new SeriesFragment();
        } else if (position == 1) {
            return new SeriesFragment();
        } else {
            return new SeriesFragment();
        }
    }

    @Override
    public int getCount() {
        return 3;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        switch (position) {
            case 0:
                return mContext.getString(R.string.frag_series_title);
            case 1:
                return mContext.getString(R.string.frag_series_title);
            case 3:
                return mContext.getString(R.string.frag_series_title);
            default:
                return mContext.getString(R.string.frag_series_title);
        }
    }
}
