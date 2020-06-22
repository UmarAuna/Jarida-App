package com.example.jaridaapp.jaridaV2.datasource;

import android.app.Application;
import android.app.ZygotePreload;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.paging.PageKeyedDataSource;

import com.example.jaridaapp.jaridaV2.model.ContentItem;
import com.example.jaridaapp.jaridaV2.model.User;
import com.example.jaridaapp.jaridaV2.networking.ApiService;
import com.example.jaridaapp.jaridaV2.networking.ApiServiceBuilder;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UserDataSource  extends PageKeyedDataSource<Long, ContentItem> {
    public static int PAGE_SIZE = 20;
    public static long FIRST_PAGE = 0;
    /*String Query = "title";*/


    @Override
    public void loadInitial(@NonNull LoadInitialParams<Long> params, @NonNull LoadInitialCallback<Long, ContentItem> callback) {

        ApiService service = ApiServiceBuilder.buildService(ApiService.class);

        Call<User> call = service.getUsers(FIRST_PAGE/*,Query*/);
        call.enqueue(new Callback<User>() {
            @Override public void onResponse(Call<User> call, Response<User> response) {
                User apiResponse = response.body();
                if (apiResponse != null) {
                    List<ContentItem> responseItems = apiResponse.getContent();
                    callback.onResult(responseItems, null, FIRST_PAGE + 1);
                    Log.d("FIRST: ", "Load Initial");
                }
            }
            @Override public void onFailure(Call<User> call, Throwable t) {
            }
        });

    }

    @Override
    public void loadBefore(@NonNull LoadParams<Long> params, @NonNull LoadCallback<Long, ContentItem> callback) {
        ApiService apiService = ApiServiceBuilder.buildService(ApiService.class);

        Call<User> call = apiService.getUsers(params.key /*, Query*/);
        call.enqueue(new Callback<User>() {
            @Override public void onResponse(Call<User> call, Response<User> response) {
                User apiResponse = response.body();
                if (apiResponse != null) {
                    List<ContentItem> responseItems = apiResponse.getContent();
                    long key;
                    if (params.key > 1) {
                        key = params.key - 1;
                    } else {
                        key = 0;
                    }
                    callback.onResult(responseItems, key);
                    Log.d("SECOND: ", "Load Before");
                }
            }
            @Override public void onFailure(Call<User> call, Throwable t) {
            }
        });
    }

    @Override
    public void loadAfter(@NonNull LoadParams<Long> params, @NonNull LoadCallback<Long, ContentItem> callback) {
        ApiService service = ApiServiceBuilder.buildService(ApiService.class);
        Call<User> call = service.getUsers(params.key /*,Query*/);
        call.enqueue(new Callback<User>() {
            @Override public void onResponse(Call<User> call, Response<User> response) {
                User apiResponse = response.body();
                if (apiResponse != null) {
                    List<ContentItem> responseItems = apiResponse.getContent();
                    callback.onResult(responseItems, params.key + 1);
                    Log.d("THIRD ", "Load After");
                }
            }
            @Override public void onFailure(Call<User> call, Throwable t) {
            }
        });
    }
}
