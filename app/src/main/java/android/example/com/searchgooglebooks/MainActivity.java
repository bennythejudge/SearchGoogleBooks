package android.example.com.searchgooglebooks;

import android.app.LoaderManager;
import android.content.Context;
import android.content.Intent;
import android.content.Loader;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

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
//    private ProgressBar mProgBar;
    private BookAdapter mAdapter;
    private TextView mEmptyStateTextView;
    private ListView bookListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d("onCreate", "entering");

        setContentView(R.layout.activity_main);
//        mProgBar = (ProgressBar) findViewById(R.id.prog_bar);
        search_term = findViewById(R.id.search_term);
        searchBtn = findViewById(R.id.search_button);
        topContext = this;
        searchBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // hide the keyboard
                if (mAdapter != null) {
                    mAdapter.clear();
                }
                InputMethodManager imm = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(search_term.getWindowToken(), 0);

                Log.d("setOnClickListener", "run: " + String.valueOf(run));
                search_term = findViewById(R.id.search_term);
                Log.d("onclicklistener", "someone clicked me: " + search_term.getText());
//                mProgBar.setVisibility(View.VISIBLE);
                LoaderManager loaderManager = getLoaderManager();
                loaderManager.initLoader(BOOKS_LOADER_ID, null,
                            (LoaderManager.LoaderCallbacks<Object>) topContext);
            }
        });
    }

    @Override
    public void onContentChanged() {
        super.onContentChanged();
        if (mAdapter != null) {
            mAdapter.clear();
        }
    }

    @Override
    public Loader<List<Book>> onCreateLoader(int i, Bundle bundle) {
        // create a new loader for the given URL
        Log.d("onCreateLoader", "entering method");
        // this is only temporary
        String searchTerm = String.valueOf(search_term.getText());
        String sUrl = "https://www.googleapis.com/books/v1/volumes?q=inauthor:" +
                URLEncoder.encode(searchTerm) +
                "&startIndex=0&maxResults=40";
        Log.d("onCreateLoader", "sUrl: " + sUrl);
        // call the API
        return new BookLoader(this, sUrl);
    }

    @Override
    public void onLoadFinished(Loader<List<Book>> loader, List<Book> bookList) {
        // here i need to switch view and show the results
        // hide the progress bar
//        mProgBar.setVisibility(View.INVISIBLE);
        Log.d("onLoadFinished", "content of the list: " + bookList.toString());
        Toast.makeText(topContext,
                "I found " + bookList.size() + " books",
                Toast.LENGTH_SHORT)
        .show();
        ShowBooks(bookList);
    }

    @Override
    public void onLoaderReset(Loader<List<Book>> loader) {
        Log.d("onLoaderReset", "entering and exiting");
        mAdapter.clear();
    }

    private void ShowBooks(List<Book> books) {
        Log.d("ShowBooks", "here now books.size(): " + books.size());
        bookListView = findViewById(R.id.list);
        mAdapter = new BookAdapter(topContext, new ArrayList<Book>());
        bookListView.setAdapter(mAdapter);
        bookListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                // when clicked send create intent to open url
                Book currentBook = mAdapter.getItem(position);

                String url = currentBook.getUrl();
                Toast.makeText(getApplicationContext(),
                        "intent at " + url,
                        Toast.LENGTH_LONG).show();
                Intent openUrlIntent = new Intent(Intent.ACTION_VIEW);
                openUrlIntent.setData(Uri.parse(url));
                startActivity(openUrlIntent);
            }
        });

        mAdapter.addAll(books);
    }
}
