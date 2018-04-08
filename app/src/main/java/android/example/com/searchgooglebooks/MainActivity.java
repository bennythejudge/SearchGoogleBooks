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

import java.util.List;

public class MainActivity extends AppCompatActivity
        implements LoaderManager.LoaderCallbacks<List<Book>> {

    private static final int BOOKS_LOADER_ID = 1;


    private Button searchBtn;
    private EditText search_term;
    private Context topContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        search_term = findViewById(R.id.search_term);
        searchBtn = findViewById(R.id.search_button);
        topContext = this;

        searchBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                search_term = findViewById(R.id.search_term);
                Log.d("onclicklistener", "someone clicked me: " + search_term.getText());
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
        String sUrl = "https://www.googleapis.com/books/v1/volumes?q=inauthor:benedetto%20lo%20giudice";
        Log.d("onCreateLoader", "sUrl: " + sUrl);

        // call the API
        return new BookLoader(this, sUrl);
    }

    @Override
    public void onLoadFinished(Loader<List<Book>> loader, List<Book> bookList) {
        Log.d("onLoadFinished", "entering and exiting");
        // here i need to switch view and show the results
    }

    @Override
    public void onLoaderReset(Loader<List<Book>> loader) {
        Log.d("onLoaderReset", "entering and exiting");
    }
}
