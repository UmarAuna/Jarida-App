package com.example.jaridaapp.jaridaV1.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.jaridaapp.jaridaV1.model.JaridaListItem;
import com.example.jaridaapp.jaridaV1.repository.Repository;

import java.util.List;

public class JaridaViewModel extends AndroidViewModel {
    private Repository countryRepository;

    public JaridaViewModel(@NonNull Application application) {
        super(application);
        countryRepository = new Repository(application);
    }

    public LiveData<List<JaridaListItem>> getAllCountry(){
        return countryRepository.getJaridaRepo();
    }

    public LiveData<JaridaListItem> postAllCountry(String title, String content, String author){
        return countryRepository.postJaridaRepo(title,content,author);
    }

    public LiveData<JaridaListItem> updateAllCountry( int id, String title, String content, String author){
        return countryRepository.updateJaridaRepo(id,title,content, author);
    }

    public LiveData<JaridaListItem> deleteAllCountry( int id){
        return countryRepository.deleteJaridaRepo(id);
    }

}
