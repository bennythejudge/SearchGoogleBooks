package android.example.com.searchgooglebooks;

import java.util.ArrayList;

/**
 * Created by neo on 04/04/2018.
 */

public class Book {
    private String mTitle;
    private String mAuthor;
    private String mImageUrl;

    public Book(String title, String author, String imageUrl) {
        mTitle = title;
        mAuthor = author;
        mImageUrl = imageUrl;
    }

    public String getTitle() { return mTitle; }
    public String getAuthors() { return mAuthor; }
}
