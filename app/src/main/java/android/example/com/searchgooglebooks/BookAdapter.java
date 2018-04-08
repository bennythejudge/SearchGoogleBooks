package android.example.com.searchgooglebooks;

import android.content.Context;
import android.graphics.drawable.GradientDrawable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

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
        String location;
        String[] aloc;

        if (listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(
                    R.layout.book_list_item, parent, false);
        }

        Book currentBook = getItem(position);

        // format the magnitude
//        DecimalFormat formatter = new DecimalFormat("0.0");
//        double mag = currentEarthquake.getMagnitude();
//        String sMag = formatter.format(mag);

//        TextView magnitudeView = (TextView) listItemView.findViewById(R.id.magnitude);
//        GradientDrawable magnitudeCircle = (GradientDrawable) magnitudeView.getBackground();

//        magnitudeView.setText(sMag);
//        // set the color of the magnitude
//        int color = getMagnitudeColor(mag);
//        magnitudeCircle.setColor(color);

//        location = currentEarthquake.getLocation();
//        aloc = splitLocation(location);

//        TextView loc1 = (TextView) listItemView.findViewById(R.id.location1);

//        TextView loc2 = (TextView) listItemView.findViewById(R.id.location2);

//        loc1.setText(aloc[0]);
//        loc2.setText(aloc[1]);

//        TextView dateView = (TextView) listItemView.findViewById(R.id.date);
//        TextView timeView = (TextView) listItemView.findViewById(R.id.time);

//        Date date = new Date(currentEarthquake.getTime());
//        DateFormat dateformat = new SimpleDateFormat("yyyy-MM-dd");
//        DateFormat timeFormat = new SimpleDateFormat("HH:mm:ss.SSS");

//        dateformat.setTimeZone(TimeZone.getTimeZone("Etc/UTC"));
//        String dateFormatted = dateformat.format(date);
//        String timeFormatted = timeFormat.format(date);

//        dateView.setText(dateFormatted);
//        timeView.setText(timeFormatted);

        return listItemView;
    }
}
