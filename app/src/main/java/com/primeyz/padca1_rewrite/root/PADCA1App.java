package com.primeyz.padca1_rewrite.root;

import android.app.Application;

import com.primeyz.padca1_rewrite.data.model.SeriesModal;

/**
 * Created by yepyaesonetun on 5/19/18.
 **/

public class PADCA1App extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        SeriesModal.getObjInstance().startloadingSimpleHabit();
    }
}
