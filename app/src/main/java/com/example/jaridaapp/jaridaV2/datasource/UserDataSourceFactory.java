package com.example.jaridaapp.jaridaV2.datasource;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;
import androidx.paging.DataSource;

import com.example.jaridaapp.jaridaV2.model.ContentItem;
import com.example.jaridaapp.jaridaV2.model.User;

public class UserDataSourceFactory extends DataSource.Factory<Long, ContentItem> {
    public MutableLiveData<UserDataSource> userLiveDataSource=new MutableLiveData<>();

    @NonNull
    @Override
    public DataSource<Long, ContentItem> create() {
        UserDataSource userDataSource = new UserDataSource();
        userLiveDataSource.postValue(userDataSource);
        return userDataSource;
    }
}
