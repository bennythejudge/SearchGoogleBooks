package android.example.com.searchgooglebooks;

import android.content.Context;
import android.graphics.drawable.GradientDrawable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;

/**
 * Created by neo on 08/04/2018.
 */

public class BookAdapter extends ArrayAdapter<Book> {

    public BookAdapter(@NonNull Context context, List<Book> books) {
        super(context, 0, books);
    }

    // prepare the display of the book data
    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View listItemView = convertView;
        String title;
        String authors;

        if (listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(
                    R.layout.book_list_item, parent, false);
        }

        Book currentBook = getItem(position);
        Log.d("getView", "position: " + position);
        Log.d("getView", "currentBook: " + currentBook.toString());
        TextView vTitle = listItemView.findViewById(R.id.title);
        vTitle.setText(currentBook.getTitle());

        TextView vAuthor = listItemView.findViewById(R.id.authors);
        vAuthor.setText(currentBook.getAuthors());
        return listItemView;
    }
}
