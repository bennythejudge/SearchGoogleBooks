package android.example.com.searchgooglebooks;

import android.content.AsyncTaskLoader;
import android.content.Context;

import java.util.List;

/**
 * Created by neo on 07/04/2018.
 */

public class BookLoader extends AsyncTaskLoader<List<Book>> {

    /** Tag for log messages */
    private static final String LOG_TAG = BookLoader.class.getName();

    private String mUrl;


    public BookLoader(Context context, String url) {
        super(context);
        mUrl = url;
    }

    // in the quakereport example it does this - what's the difference?
    //    protected void onStartLoading() {
    //        forceLoad();
    //    }

    @Override
    protected void onStartLoading() {
        super.onStartLoading();
    }

    @Override
    public List<Book> loadInBackground() {
        if (mUrl == null) {
            return null;
        }
        // Perform the network request, parse the response, and extract a list of earthquakes.
        List<Book> books = QueryUtils.fetchBooksData(mUrl);
        return books;
    }
}