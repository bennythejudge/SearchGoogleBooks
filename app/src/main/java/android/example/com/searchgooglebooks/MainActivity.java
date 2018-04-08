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
        setContentView(R.layout.activity_main);

        bookListView = (ListView) findViewById(R.id.list);

        search_term = findViewById(R.id.search_term);
        searchBtn = findViewById(R.id.search_button);
        topContext = this;
        mEmptyStateTextView = (TextView) findViewById(R.id.empty_view);
        mProgBar = (ProgressBar) findViewById(R.id.prog_bar);
        mAdapter = new BookAdapter(this, new ArrayList<Book>());
        bookListView.setAdapter(mAdapter);

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
                run++;
//                } else {
//                    getLoaderManager().restartLoader(
//                            0, null, (LoaderManager.LoaderCallbacks<Object>) topContext);
//                    run++;
//                }
            }
        });
    }

    @Override
    public Loader<List<Book>> onCreateLoader(int i, Bundle bundle) {
        // create a new loader for the given URL
        Log.d("onCreateLoader", "entering method");
        // this is only temporary
        String sUrl = "https://www.googleapis.com/books/v1/volumes?q=inauthor:benedetto%20lo%20giudice";
        Log.d("onCreateLoader", "sUrl: " + sUrl);

        // call the API
        return new BookLoader(this, sUrl);
    }

    @Override
    public void onLoadFinished(Loader<List<Book>> loader, List<Book> bookList) {
        Log.d("onLoadFinished", "entering and exiting");
        // here i need to switch view and show the results
        // hide the progress bar
        mProgBar.setVisibility(View.INVISIBLE);

        // here update the UI this is called when the loader has finished loading
        mAdapter.clear();
        Log.d("onLoadFinished", "now update the UI with: " + bookList);
        if (bookList != null && ! bookList.isEmpty()) {
            mAdapter.addAll(bookList);
        } else {
            mProgBar.setVisibility(View.INVISIBLE);
            mEmptyStateTextView.setText(R.string.no_books);
        }

    }

    @Override
    public void onLoaderReset(Loader<List<Book>> loader) {
        Log.d("onLoaderReset", "entering and exiting");
    }
}
