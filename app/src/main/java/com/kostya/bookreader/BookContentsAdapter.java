package com.kostya.bookreader;

import android.app.Fragment;
import android.app.FragmentManager;
import android.support.v13.app.FragmentStatePagerAdapter;

/**
 * Created by konstantin on 1/31/2017.
 */

public class BookContentsAdapter extends FragmentStatePagerAdapter {

    private final BookContents mBookContents;

    public BookContentsAdapter(FragmentManager fm, BookContents bookContents) {
        super(fm);
        mBookContents = bookContents;
    }

    @Override
    public Fragment getItem(int position) {
        String file = mBookContents.getChapterFile(position);

        return SimpleContentFragment.newInstance(Constants.PATH_ASSET_BOOKS + file);
    }

    @Override
    public int getCount() {
        return mBookContents.getChapterCount();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return mBookContents.getChapterTitle(position);
    }
}
