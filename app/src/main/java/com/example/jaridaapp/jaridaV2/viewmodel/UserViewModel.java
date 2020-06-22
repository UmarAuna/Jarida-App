package com.example.jaridaapp.jaridaV2.viewmodel;

import android.app.Application;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;
import androidx.paging.LivePagedListBuilder;
import androidx.paging.PagedList;

import com.example.jaridaapp.jaridaV2.datasource.UserDataSource;
import com.example.jaridaapp.jaridaV2.datasource.UserDataSourceFactory;
import com.example.jaridaapp.jaridaV2.model.ContentItem;
import com.example.jaridaapp.jaridaV2.model.User;

import java.util.Objects;

public class UserViewModel extends ViewModel {
    public LiveData<PagedList<ContentItem>> userPagedList;
    private LiveData<UserDataSource> liveDataSource;

    public UserViewModel() {
        init();
    }
    private void init() {
        UserDataSourceFactory itemDataSourceFactory = new UserDataSourceFactory();
        liveDataSource = itemDataSourceFactory.userLiveDataSource;
        PagedList.Config config = new PagedList.Config.Builder()
                .setEnablePlaceholders(false)
                .setPageSize(UserDataSource.PAGE_SIZE)
                .build();
        userPagedList = new LivePagedListBuilder<>(itemDataSourceFactory, config).build();
    }

    public void refresh(){
       Objects.requireNonNull(liveDataSource.getValue()).invalidate();
    }

}
