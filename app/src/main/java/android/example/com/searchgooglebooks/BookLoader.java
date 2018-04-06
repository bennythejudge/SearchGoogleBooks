package android.example.com.searchgooglebooks;

import android.content.AsyncTaskLoader;
import android.content.Context;

import java.util.List;

/**
 * Created by neo on 07/04/2018.
 */

public class BookLoader extends AsyncTaskLoader<List<Book>> {

    private String mUrl;


    public BookLoader(Context context, String url) {
        super(context);
        mUrl = url;
    }


    @Override
    protected void onStartLoading() {
        super.onStartLoading();
    }

    @Override
    public List<Book> loadInBackground() {
        return null;
    }
}
