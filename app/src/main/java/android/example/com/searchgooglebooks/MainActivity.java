package android.example.com.searchgooglebooks;

import android.app.LoaderManager;
import android.content.Context;
import android.content.Loader;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity
        implements LoaderManager.LoaderCallbacks<List<Book>> {

    private static final int BOOKS_LOADER_ID = 1;


    private Button searchBtn;
    private EditText search_term;
    private Context topContext;
    private static int run = 0;
    private ProgressBar mProgBar;
    private BookAdapter mAdapter;
    private TextView mEmptyStateTextView;
    private ListView bookListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d("onCreate", "entering");

        setContentView(R.layout.activity_main);
        mProgBar = (ProgressBar) findViewById(R.id.prog_bar);
        search_term = findViewById(R.id.search_term);
        searchBtn = findViewById(R.id.search_button);
        topContext = this;
        searchBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d("setOnClickListener", "run: " + String.valueOf(run));
                search_term = findViewById(R.id.search_term);
                Log.d("onclicklistener", "someone clicked me: " + search_term.getText());
                mProgBar.setVisibility(View.VISIBLE);
                LoaderManager loaderManager = getLoaderManager();
                loaderManager.initLoader(BOOKS_LOADER_ID, null,
                            (LoaderManager.LoaderCallbacks<Object>) topContext);
            }
        });
    }

    @Override
    public Loader<List<Book>> onCreateLoader(int i, Bundle bundle) {
        // create a new loader for the given URL
        Log.d("onCreateLoader", "entering method");
        // this is only temporary
        String searchTerm = String.valueOf(search_term.getText());
        String sUrl = "https://www.googleapis.com/books/v1/volumes?q=inauthor:" +
                URLEncoder.encode(searchTerm);
        Log.d("onCreateLoader", "sUrl: " + sUrl);
        // call the API
        return new BookLoader(this, sUrl);
    }

    @Override
    public void onLoadFinished(Loader<List<Book>> loader, List<Book> bookList) {
        // here i need to switch view and show the results
        // hide the progress bar
        mProgBar.setVisibility(View.INVISIBLE);
        Log.d("onLoadFinished", "content of the list: " + bookList.toString());
        // here update the UI this is called when the loader has finished loading
//        if (mAdapter != null) {
//            mAdapter.clear();
//        } else {
//            Log.d("onLoadFinished", "mAdapter is null!!!!!!!!");
//        }
//        Log.d("onLoadFinished", "now update the UI with: " + bookList);
//        if (bookList != null && ! bookList.isEmpty()) {
//            mAdapter.addAll(bookList);
//        } else {
//            mProgBar.setVisibility(View.INVISIBLE);
//            mEmptyStateTextView.setText(R.string.no_books);
//        }
    }

    @Override
    public void onLoaderReset(Loader<List<Book>> loader) {
        Log.d("onLoaderReset", "entering and exiting");
    }
}
