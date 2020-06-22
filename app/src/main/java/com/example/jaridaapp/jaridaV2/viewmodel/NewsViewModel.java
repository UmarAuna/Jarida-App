package com.example.jaridaapp.jaridaV2.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.jaridaapp.jaridaV1.model.JaridaListItem;
import com.example.jaridaapp.jaridaV1.repository.Repository;
import com.example.jaridaapp.jaridaV2.Repository.RepositoryPost;
import com.example.jaridaapp.jaridaV2.model.ContentItem;

public class NewsViewModel extends AndroidViewModel {
    private RepositoryPost countryRepository;
    public NewsViewModel(@NonNull Application application) {
        super(application);
        countryRepository = new RepositoryPost(application);
    }

    public LiveData<ContentItem> postAllNews(String title, String content, String author){
        return countryRepository.postNewsRepo(title,content,author);
    }
}
