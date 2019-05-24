package danieer.galvez.testrappi.network.client;

import android.text.TextUtils;
import android.util.Log;

import java.util.concurrent.TimeUnit;

import danieer.galvez.testrappi.application.TestRappiApplication;
import danieer.galvez.testrappi.network.network.utils.NetworkUtils;
import okhttp3.Cache;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import retrofit2.Retrofit;
import retrofit2.converter.jackson.JacksonConverterFactory;

public class ApiClient {

    private static Retrofit retrofit = null;
    private static OkHttpClient okHttpClient = null;

    private static Long cacheSize = (long) (5 * 1024 * 1024);
    public static Cache cache = new Cache(TestRappiApplication.getAppContext().getCacheDir(), cacheSize);

    private static OkHttpClient.Builder builder;
    private static Retrofit.Builder retrofitBuilder;

    static {

        builder = new OkHttpClient.Builder();
        builder.connectTimeout(1, TimeUnit.MINUTES);
        builder.readTimeout(45, TimeUnit.SECONDS);
        builder.writeTimeout(45, TimeUnit.SECONDS);
        builder.cache(cache).addInterceptor(chain -> {
            Request request = chain.request();
            if (NetworkUtils.checkNetworkConnection(TestRappiApplication.getAppContext()))
                request.newBuilder().header("Cache-Control", "public, max-age=" + 5).build();
            else
                request.newBuilder().header("Cache-Control", "public, only-if-cached, max-stale=" + 60 * 60 * 24 * 7).build();

            return chain.proceed(request);
        });

        retrofitBuilder = new Retrofit.Builder()
                .baseUrl(NetworkUtils.BASE_URL)
                .addConverterFactory(JacksonConverterFactory.create());

    }

    public static <S> S createService(Class<S> serviceClass) {
        return createService(serviceClass, null);
    }

    public static <S> S createService(Class<S> serviceClass, String accessToken) {
        if (!TextUtils.isEmpty(accessToken)) {
            Log.d(ApiClient.class.getName(), "Empty access token");
        }

        retrofitBuilder.client(builder.build());
        retrofit = retrofitBuilder.build();

        return retrofit.create(serviceClass);
    }

}
