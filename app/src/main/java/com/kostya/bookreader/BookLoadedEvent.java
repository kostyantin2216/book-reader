package com.kostya.bookreader;

/**
 * Created by konstantin on 1/30/2017.
 */

public class BookLoadedEvent {

    private final BookContents contents;

    public BookLoadedEvent(BookContents contents) {
        this.contents = contents;
    }

}
