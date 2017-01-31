package com.kostya.bookreader;

import android.app.Activity;
import android.app.Fragment;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.os.Process;
import android.util.Log;

import com.google.gson.Gson;

import org.greenrobot.eventbus.EventBus;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.concurrent.atomic.AtomicReference;

/**
 * Created by konstantin on 1/30/2017.
 */

public class ModelFragment extends Fragment {

    private AtomicReference<BookContents> mBookContents;
    private AtomicReference<SharedPreferences> mSharedPrefs;

    private volatile boolean loadingInProgress = false;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setRetainInstance(true);
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);

        if(mBookContents.get() == null && !loadingInProgress) {
            new LoadThread(activity).start();
        }
    }

    public BookContents getBookContents() {
        return mBookContents.get();
    }

    public SharedPreferences getPreferences() {
        return mSharedPrefs.get();
    }

    private class LoadThread extends Thread {

        private final Context mContext;

        public LoadThread(Context context) {
            this.mContext = context.getApplicationContext();
        }

        @Override
        public void run() {
            loadingInProgress = true;
            mSharedPrefs.set(PreferenceManager.getDefaultSharedPreferences(mContext));

            Process.setThreadPriority(Process.THREAD_PRIORITY_BACKGROUND);

            Gson gson = new Gson();

            try {
                InputStream is = mContext.getAssets().open("book/contents.json");
                BufferedReader reader = new BufferedReader(new InputStreamReader(is));

                BookContents bookContents = gson.fromJson(reader, BookContents.class);
                mBookContents.set(bookContents);

                loadingInProgress = false;

                EventBus.getDefault().post(new BookLoadedEvent(bookContents));
            } catch(IOException e) {
                loadingInProgress = false;
                Log.e(getClass().getSimpleName(), "error while loading book contents.", e);
            }
        }
    }

}
