package com.kostya.bookreader;

import java.util.List;

/**
 * Created by konstantin on 1/30/2017.
 */

public class BookContents {

    private final List<Chapter> chapters;

    public BookContents(List<Chapter> chapters) {
        this.chapters = chapters;
    }

    public String getChapterFile(int position) {
        return chapters.get(position).file;
    }

    public String getChapterTitle(int position) {
        return chapters.get(position).title;
    }

    public int getChapterCount() {
        return chapters.size();
    }

    public static class Chapter {
        public final String file;
        public final String title;

        public Chapter(String file, String title) {
            this.file = file;
            this.title = title;
        }
    }

}
