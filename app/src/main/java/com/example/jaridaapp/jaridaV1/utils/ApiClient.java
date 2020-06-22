package com.example.jaridaapp.jaridaV1.utils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.Cache;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiClient {

    private static final String BASE_URL = "http://192.168.8.100:8080/";
    //private static final String NEWS_URL = "http://192.168.8.100:8080/";

    private static Retrofit retrofit = null;

    // Check if the device has Internet or not
    public static Boolean hasNetwork(Context context) {
        boolean isConnected = false; // Initial Value
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetwork = null;
        if (connectivityManager != null)
            activeNetwork = connectivityManager.getActiveNetworkInfo();
        if (activeNetwork != null && activeNetwork.isConnectedOrConnecting())
            isConnected = true;
        return isConnected;
    }

    /*
       Get a cache-enabled Retrofit,
       with 10MB cache,
       and which loads new data if the device is connected to the Internet;
       if not, loads cached data that stays cached for 7 days
   */
    private static HttpLoggingInterceptor logger = new HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY);
    public static Retrofit getCacheEnabledRetrofit(final Context context) {
        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .addInterceptor(logger)
                .cache(new Cache(context.getCacheDir(), 10 * 1024 * 1024))// Setting the cache size to 10MB
                .connectTimeout(20, TimeUnit.SECONDS) //Sets the default connect timeout for new connections.
                .readTimeout(20, TimeUnit.SECONDS) //Sets the default read timeout for new connections.
                .writeTimeout(20, TimeUnit.SECONDS) //Sets the default write timeout for new connections.
                .callTimeout(20, TimeUnit.SECONDS) //Sets the default timeout for complete calls.
                .followSslRedirects(true) //Configure this client to follow redirects from HTTPS to HTTP and from HTTP to HTTPS.
                .retryOnConnectionFailure(true) //Configure this client to retry or not when a connectivity problem is encountered.
                .addInterceptor(new Interceptor() {
                    @Override
                    public Response intercept(Chain chain) throws IOException {
                        Request request = chain.request();
                        // If the device is connected to the Internet, fetch new data immediately
                        if (hasNetwork(context)) {
                            request = request.newBuilder().header("Cache-Control", "public, max-age=" + 1).build();
                        }
                        // Else, load cached data that stays cached up to 7 days
                        else
                            request = request.newBuilder().header("Cache-Control", "public, only-if-cached, max-stale=" + 60 * 60 * 24 * 7).build();
                        return chain.proceed(request);
                    }
                }).build();


        Gson gson = new GsonBuilder()
                .setLenient().serializeNulls()
                .create();

        // Create the Retrofit instance with the above configuration
        return new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create(gson))
                .client(okHttpClient)
                .baseUrl(BASE_URL)
                .build();
    }



   /* //building retrofit object
    public static Retrofit getNewsClient() {
        if(retrofit == null){
            Gson gson = new GsonBuilder()
                    .setLenient().serializeNulls()
                    .create();

            retrofit = new Retrofit.Builder()
                    .baseUrl(NEWS_URL)
                    .addConverterFactory(GsonConverterFactory.create(gson))
                    .build();
        }
        return retrofit;
    }*/
}
