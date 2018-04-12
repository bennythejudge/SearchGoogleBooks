package android.example.com.searchgooglebooks;

import java.util.ArrayList;

/**
 * Created by neo on 04/04/2018.
 */

public class Book {
    private String mTitle;
    private String mAuthor;
    private String mImageUrl;
    private String mUrl;

    public Book(String title, String author, String imageUrl, String url) {
        mTitle = title;
        mAuthor = author;
        mImageUrl = imageUrl;
        mUrl = url;
    }

    public String getTitle() { return mTitle; }
    public String getAuthors() { return mAuthor; }
}
