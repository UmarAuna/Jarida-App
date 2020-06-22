package com.example.jaridaapp.jaridaV2.Repository;

import android.app.Application;
import android.util.Log;
import android.widget.Toast;

import androidx.lifecycle.MutableLiveData;

import com.example.jaridaapp.jaridaV1.model.JaridaListItem;
import com.example.jaridaapp.jaridaV1.utils.ApiClient;
import com.example.jaridaapp.jaridaV2.networking.ApiService;
import com.example.jaridaapp.jaridaV2.model.ContentItem;
import com.example.jaridaapp.jaridaV2.networking.ApiServiceBuilder;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RepositoryPost {
    private Application application;
    private ApiService apiService;
    private List<ContentItem> countryList;
    private MutableLiveData<List<ContentItem>> mutableLiveData = new MutableLiveData<>();

    public RepositoryPost(Application application) {
        this.application = application;
    }

    public MutableLiveData<ContentItem> postNewsRepo(String title, String content, String author) {
        final MutableLiveData<ContentItem> newsData = new MutableLiveData<>();
        apiService = ApiServiceBuilder.getCacheEnabledRetrofit(application).create(ApiService.class);
        apiService.postJarida(title, content, author).enqueue(new Callback<ContentItem>() {
            @Override
            public void onResponse(Call<ContentItem> call, Response<ContentItem> response) {
                if (response.isSuccessful()) {
                    assert response.body() != null;
                    String title = response.body().getTitle();
                    String content = response.body().getContent();
                    String author = response.body().getAuthor();

                    ContentItem jaridaListItem = new ContentItem();
                    jaridaListItem.setTitle(title);
                    jaridaListItem.setContent(content);
                    jaridaListItem.setAuthor(author);
                    newsData.setValue(jaridaListItem);
                } else {
                    mutableLiveData.setValue(null);
                    if (response.code() == 404) {
                        Toast.makeText(application, "This is a title that exists", Toast.LENGTH_SHORT).show();
                        Log.d("EXIST", "This is a title that exists");
                    }
                }

            }

            @Override
            public void onFailure(Call<ContentItem> call, Throwable t) {
                try {
                    newsData.setValue(null);
                    Log.d("TAG", "onFailure: " + t);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
        return newsData;
    }
}
