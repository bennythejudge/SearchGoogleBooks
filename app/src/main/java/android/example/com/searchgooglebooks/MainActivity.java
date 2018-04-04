package android.example.com.searchgooglebooks;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    private Button searchBtn;
    private EditText search_term;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        search_term = findViewById(R.id.search_term);
        searchBtn = findViewById(R.id.search_button);
        searchBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                search_term = findViewById(R.id.search_term);
                Log.d("onclicklistener", "someone clicked me: " + search_term.getText());
            }
        });
    }
}
