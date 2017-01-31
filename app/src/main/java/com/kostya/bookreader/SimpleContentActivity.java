package com.kostya.bookreader;

import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;

/**
 * Created by konstantin on 1/31/2017.
 */

public class SimpleContentActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if(getFragmentManager().findFragmentById(android.R.id.content) == null) {
            String file = getIntent().getStringExtra(Constants.KEY_FILE);
            Fragment frag = SimpleContentFragment.newInstance(file);

            getFragmentManager()
                    .beginTransaction()
                    .add(android.R.id.content, frag)
                    .commit();
        }
    }
}
