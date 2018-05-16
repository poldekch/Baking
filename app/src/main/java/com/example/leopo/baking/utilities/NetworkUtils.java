package com.example.leopo.baking.utilities;

import android.net.Uri;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Scanner;

public class NetworkUtils {

    final public static String ID = "id";
    final public static String NAME = "name";
    final public static String INGREDIENTS = "ingredients";
    final public static String QUANTITY = "quantity";
    final public static String MEASURE = "measure";
    final public static String INGREDIENT = "ingredient";
    final public static String STEPS = "steps";
    final public static String STEP_ID = "ID";
    final public static String SHORT_DESCRIPTION = "shortDescription";
    final public static String DESCRIPTION = "description";
    final public static String VIDEO_URL = "videoURL";
    final public static String THUMBNAIL_URL = "thumbnailURL";
    final public static String SERVINGS = "servings";
    final public static String IMAGE = "image";

    final public static String RECIPIES_URL = "https://d17h27t6h515a5.cloudfront.net/topher/2017/May/59121517_baking/baking.json";

    public static URL builderUrl() {
        Uri buildUri = Uri.parse(RECIPIES_URL);

        URL url = null;
        try {
            url = new URL(buildUri.toString());
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        
        return url;
    }

    public static String getApiResponse(URL url) throws IOException {
        HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
        try {
            InputStream in = urlConnection.getInputStream();
            Scanner scanner = new Scanner(in);
            scanner.useDelimiter("\\A");
            boolean hasInput = scanner.hasNext();
            if (hasInput) {
                return scanner.next();
            } else {
                return null;
            }
        } finally {
            urlConnection.disconnect();
        }
    }
}