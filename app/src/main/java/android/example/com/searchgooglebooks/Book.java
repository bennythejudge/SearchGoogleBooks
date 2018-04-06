package android.example.com.searchgooglebooks;

import java.util.ArrayList;

/**
 * Created by neo on 04/04/2018.
 */

public class Book {
    private String mTitle;
    private ArrayList<String> mAuthors;
    private String mImageUrl;

    private Book(String title, ArrayList<String> authors, String imageUrl) {
        mTitle = title;
        mAuthors = authors;
        mImageUrl = imageUrl;
    }
}
