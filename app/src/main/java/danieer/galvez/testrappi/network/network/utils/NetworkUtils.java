package danieer.galvez.testrappi.network.network.utils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

public class NetworkUtils {

    public static final String BASE_URL = "http://api.themoviedb.org/3/";
    public static final String API_KEY = "7a03adca4702ab3dc849545e0c707d60";
    public static final String IMAGE_BASE_URL = "https://image.tmdb.org/t/p/w200/";
    public static final String BACKDROP_BASE_URL = "https://image.tmdb.org/t/p/w780/";

    public static final String YOUTUBE_URL = "https://www.youtube.com/watch?v=";

    public static final String POPULAR_MOVIES_ENDPOINT = "movie/popular";

    public static final String TOP_RATED_MOVIES_ENDPOINT = "movie/top_rated";

    public static final String UPCOMING_MOVIES_ENDPOINT = "movie/upcoming";

    public static final String MOVIE_DETAILS_ENDPOINT = "movie/{movie_id}";

    public static final String MOVIE_ID = "movie_id";

    public static final String API_KEY_QUERY = "api_key";

    public static final String PAGE_NO = "page";

    public static final String APPEND_TO_RESPONSE = "append_to_response";

    public static boolean checkNetworkConnection(Context context) {
        boolean connected = false;
        ConnectivityManager connMgr = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo wifi = connMgr.getNetworkInfo(ConnectivityManager.TYPE_WIFI);
        NetworkInfo mobile = connMgr.getNetworkInfo(ConnectivityManager.TYPE_MOBILE);
        if (wifi != null && wifi.isConnected() || mobile != null && mobile.isConnected())
            connected = true;
        return connected;
    }



}
