package com.kostya.bookreader;

import android.app.Activity;
import android.os.StrictMode;
import android.support.v4.view.ViewPager;
import android.os.Bundle;

import org.greenrobot.eventbus.EventBus;

public class MainActivity extends Activity {

    private ViewPager mPager;
    private BookContentsAdapter mAdapter;
    private ModelFragment mModelFrag = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setUpStrictMode();
        mPager = (ViewPager) findViewById(R.id.pager);
    }

    @Override
    protected void onStart() {
        super.onStart();
        EventBus.getDefault().register(this);

        if(mAdapter == null) {
            mModelFrag = (ModelFragment) getFragmentManager().findFragmentByTag(Constants.TAG_MODEL);

            if(mModelFrag == null) {
                mModelFrag = new ModelFragment();

                getFragmentManager()
                        .beginTransaction()
                        .add(mModelFrag, Constants.TAG_MODEL)
                        .commit();
            } else if(mModelFrag.getBookContents() != null) {
                mPager.setKeepScreenOn(mModelFrag.getPreferences().getBoolean(Constants.PREF_KEEP_SCREEN_ON, false));
            }
        }

        if(mModelFrag.getPreferences() != null) {
            mPager.setKeepScreenOn(mModelFrag.getPreferences().getBoolean(Constants.PREF_KEEP_SCREEN_ON, false));
        }
    }

    private void setUpPager(BookContents bookContents) {

    }

    private void setUpStrictMode() {
        StrictMode.ThreadPolicy.Builder builder=
                new StrictMode.ThreadPolicy.Builder()
                        .detectAll()
                        .penaltyLog();

        if (BuildConfig.DEBUG) {
            builder.penaltyFlashScreen();
        }

        StrictMode.setThreadPolicy(builder.build());
    }

}
