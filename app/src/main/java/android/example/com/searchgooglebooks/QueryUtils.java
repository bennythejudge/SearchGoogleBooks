package android.example.com.searchgooglebooks;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by neo on 07/04/2018.
 */

public class QueryUtils {

    private static final String LOG_TAG = QueryUtils.class.getSimpleName();

    private QueryUtils() {
        // empty constructor
    }

    public static List<Book> fetchBooksData(String requestUrl) {


        Log.d("fetchBooksData", "Entering method");

        // Create URL object
        URL url = createUrl(requestUrl);

        // Perform HTTP request to the URL and receive a JSON response back
        String jsonResponse = null;
        try {
            jsonResponse = makeHttpRequest(url);
        } catch (IOException e) {
            Log.e(LOG_TAG, "Error making the HTTP request.", e);
        }

        Log.d(LOG_TAG, "jsonResponse: " + jsonResponse);

        // Extract relevant fields from the JSON response and create a list of {@link Earthquake}s
        List<Book> books = extractBooks(jsonResponse);

        // Return the list of {@link Earthquake}s
        return books;
    }

    /**
     * Returns new URL object from the given string URL.
     */
    private static URL createUrl(String stringUrl) {
        URL url = null;
        try {
            url = new URL(stringUrl);
        } catch (MalformedURLException e) {
            Log.e(LOG_TAG, "Error building the URL ", e);
        }
        Log.d("createUrl", "the url: " + url);
        return url;
    }
    private static String makeHttpRequest(URL url) throws IOException {

        Log.d("makeHttpRequest", "entering the method");


        String jsonResponse = "";


        // If the URL is null, then return early.
        if (url == null) {
            Log.e("makeHttpRequest", "ERROR: url is null!");
            return jsonResponse;
        }

        HttpURLConnection urlConnection = null;
        InputStream inputStream = null;
        try {
            Log.e("makeHttpRequest", "doing urlconnection");
            urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setReadTimeout(1000 /* milliseconds */);
            urlConnection.setConnectTimeout(3000 /* milliseconds */);
            urlConnection.setRequestMethod("GET");
            urlConnection.connect();

            // If the request was successful (response code 200),
            // then read the input stream and parse the response.
            if (urlConnection.getResponseCode() == 200) {
                Log.d(LOG_TAG, "HTTP GET successful!");
                inputStream = urlConnection.getInputStream();
                jsonResponse = readFromStream(inputStream);
            } else {
                Log.e(LOG_TAG, "Error response code: " + urlConnection.getResponseCode());
            }
        } catch (IOException e) {
            Log.e(LOG_TAG, "Error retrieving the book search JSON results.", e);
            throw e;
        } finally {
            if (urlConnection != null) {
                urlConnection.disconnect();
            }
            if (inputStream != null) {
                // Closing the input stream could throw an IOException, which is why
                // the makeHttpRequest(URL url) method signature specifies than an IOException
                // could be thrown.
                inputStream.close();
            }
        }
        return jsonResponse;
    }
    private static String readFromStream(InputStream inputStream) throws IOException {
        StringBuilder output = new StringBuilder();
        if (inputStream != null) {
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream, Charset.forName("UTF-8"));
            BufferedReader reader = new BufferedReader(inputStreamReader);
            String line = reader.readLine();
            while (line != null) {
                output.append(line);
                line = reader.readLine();
            }
        }
        return output.toString();
    }
    /**
     * Return a list of {@link Book} objects that has been built up from
     * parsing a JSON response.
     */
    public static ArrayList<Book> extractBooks(String response) {

        String authors;

        Log.d(LOG_TAG, "response: " + response);

        // Create an empty ArrayList that we can start adding earthquakes to
        ArrayList<Book> books = new ArrayList<>();

        // Try to parse the SAMPLE_JSON_RESPONSE. If there's a problem with the way the JSON
        // is formatted, a JSONException exception object will be thrown.
        // Catch the exception so the app doesn't crash, and print the error message to the logs.
        try {
            // TODO: Parse the response given by the SAMPLE_JSON_RESPONSE string and
            // build up a list of Earthquake objects with the corresponding data.
            /*
            Convert SAMPLE_JSON_RESPONSE String into a JSONObject
            Extract “features” JSONArray
            Loop through each feature in the array
            Get earthquake JSONObject at position i
            Get “properties” JSONObject
            Extract “mag” for magnitude
            Extract “place” for location
            Extract “time” for time
            Create Earthquake java object from magnitude, location, and time
            Add earthquake to list of earthquakes
            */
            // convert string into a JSON object
            JSONObject jsonObj = new JSONObject(response);
            Log.d(LOG_TAG, "converted string to JSON - passed");
            JSONArray json_aBooks = jsonObj.getJSONArray("items");
            Log.d("extractBooks",
                    "extracted the array of books from the JSON - entering loop");
            for (int i=0; i<json_aBooks.length(); i++) {
                JSONObject c = json_aBooks.getJSONObject(i);
                Log.d("extractBooks",
                        "examining item " + String.valueOf(i) + " of the array");
                String kind = c.getString("kind");
                Log.d("extractBooks",
                        "kind: " + kind);
                JSONObject volumeInfo = c.getJSONObject("volumeInfo");
                String title = volumeInfo.getString("title");
                int n = volumeInfo.getJSONArray("authors").length();
                if (n > 1) {
                    authors = "Multiple authors";
                } else {
                    authors = "Extract me from the JSON";
                }
//                double mag = p.getDouble("mag");
//                String location = p.getString("place");
//                Long time = p.getLong("time");
////                Log.v("EQ", "mag: " + mag + " place: " + location + " time: " + time);
//                // convert time to long and convert then to Date format
//                // no longer need to convert as I can extract from json as long
//                // Date date = new Date(Long.parseLong(time));
//                // extract the URL
//                String url = p.getString("url");
////                Log.d("QueryUtils", "url: " + url);
                books.add(new Book(title, authors, ""));
            }
        } catch (JSONException e) {
            // If an error is thrown when executing any of the above statements in the "try" block,
            // catch the exception here, so the app doesn't crash. Print a log message
            // with the message from the exception.
            Log.e("QueryUtils", "Error parsing the book search JSON results", e);
        }
        // Return the list of earthquakes
        return books;
    }
}
