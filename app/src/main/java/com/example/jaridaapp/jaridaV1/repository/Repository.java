package com.example.jaridaapp.jaridaV1.repository;

import android.app.Application;
import android.util.Log;
import android.widget.Toast;

import androidx.lifecycle.MutableLiveData;

import com.example.jaridaapp.jaridaV1.model.JaridaListItem;
import com.example.jaridaapp.jaridaV1.utils.ApiClient;
import com.example.jaridaapp.jaridaV1.utils.ApiService;

import org.jetbrains.annotations.NotNull;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Repository {
    private Application application;
    private ApiService apiService;
    private List<JaridaListItem> countryList;
    private MutableLiveData<List<JaridaListItem>> mutableLiveData = new MutableLiveData<>();

    public Repository(Application application){
        this.application = application;
    }


    public MutableLiveData<List<JaridaListItem>> getJaridaRepo(){
        final MutableLiveData<List<JaridaListItem>> jaridaData = new MutableLiveData<>();
        apiService = ApiClient.getCacheEnabledRetrofit(application).create(ApiService.class);
        apiService.getJarida().enqueue(new Callback<List<JaridaListItem>>() {
            @Override
            public void onResponse(@NotNull Call<List<JaridaListItem>> call, @NotNull Response<List<JaridaListItem>> response) {
                if(response.isSuccessful()){
                    countryList = response.body();
                    mutableLiveData.setValue(countryList);
                    Log.d("RESPONSE", ""+ response.body());
                }
            }

            @Override
            public void onFailure(@NotNull Call<List<JaridaListItem>> call, @NotNull Throwable t) {
                try{
                    jaridaData.setValue(null);
                    Log.d("TAG", "onFailure: " + t);
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        });
        return mutableLiveData;
    }

    public MutableLiveData <JaridaListItem> postJaridaRepo(String title, String content, String author){
        final MutableLiveData<JaridaListItem> jaridaData = new MutableLiveData<>();
        apiService = ApiClient.getCacheEnabledRetrofit(application).create(ApiService.class);
        apiService.postJarida(title, content, author).enqueue(new Callback<JaridaListItem>() {
            @Override
            public void onResponse(Call<JaridaListItem> call, Response<JaridaListItem> response) {
                if (response.isSuccessful()){
                    assert response.body() != null;
                    String title = response.body().getTitle();
                    String content = response.body().getContent();
                    String author = response.body().getAuthor();

                    JaridaListItem jaridaListItem = new JaridaListItem();
                    jaridaListItem.setTitle(title);
                    jaridaListItem.setContent(content);
                    jaridaListItem.setAuthor(author);
                    jaridaData.setValue(jaridaListItem);
                }else{
                    mutableLiveData.setValue(null);
                    if(response.code() == 404){
                        Toast.makeText(application, "This is a title that exists", Toast.LENGTH_SHORT).show();
                        Log.d("EXIST", "This is a title that exists");
                    }
                }

            }

            @Override
            public void onFailure(Call<JaridaListItem> call, Throwable t) {
                try{
                    jaridaData.setValue(null);
                    Log.d("TAG", "onFailure: " + t);
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        });
        return jaridaData;
    }

    public MutableLiveData <JaridaListItem> updateJaridaRepo( int id,String title, String content, String author){
        final MutableLiveData<JaridaListItem> jaridaData = new MutableLiveData<>();
        apiService = ApiClient.getCacheEnabledRetrofit(application).create(ApiService.class);
        apiService.updateJarida(id,title, content, author).enqueue(new Callback<JaridaListItem>() {
            @Override
            public void onResponse(Call<JaridaListItem> call, Response<JaridaListItem> response) {
                if (response.isSuccessful()){
                    assert response.body() != null;
                    int id = response.body().getId();
                    String title = response.body().getTitle();
                    String content = response.body().getContent();
                    String author = response.body().getAuthor();

                    JaridaListItem jaridaListItem = new JaridaListItem();
                    jaridaListItem.setId(id);
                    jaridaListItem.setTitle(title);
                    jaridaListItem.setContent(content);
                    jaridaListItem.setAuthor(author);
                    jaridaData.setValue(jaridaListItem);
                }else{
                    mutableLiveData.setValue(null);
                    if(response.code() == 404){
                        Toast.makeText(application, "Jarida not found for this id", Toast.LENGTH_SHORT).show();
                        Log.d("EXIST", "Jarida not found for this id");
                    }
                }

            }

            @Override
            public void onFailure(Call<JaridaListItem> call, Throwable t) {
                try{
                    jaridaData.setValue(null);
                    Log.d("TAG", "onFailure: " + t);
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        });
        return jaridaData;
    }

    public MutableLiveData <JaridaListItem> deleteJaridaRepo( int id){
        final MutableLiveData<JaridaListItem> jaridaData = new MutableLiveData<>();
        apiService = ApiClient.getCacheEnabledRetrofit(application).create(ApiService.class);
        apiService.deleteJarida(id).enqueue(new Callback<JaridaListItem>() {
            @Override
            public void onResponse(Call<JaridaListItem> call, Response<JaridaListItem> response) {
                if (response.isSuccessful()){
                    assert response.body() != null;
                    int id = response.body().getId();

                    JaridaListItem jaridaListItem = new JaridaListItem();
                    jaridaListItem.setId(id);
                    jaridaData.setValue(jaridaListItem);
                }else{
                    mutableLiveData.setValue(null);
                    if(response.code() == 404){
                        Toast.makeText(application, "Jarida not found for this id", Toast.LENGTH_SHORT).show();
                        Log.d("EXIST", "Jarida not found for this id");
                    }
                }

            }

            @Override
            public void onFailure(Call<JaridaListItem> call, Throwable t) {
                try{
                    jaridaData.setValue(null);
                    Log.d("TAG", "onFailure: " + t);
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        });
        return jaridaData;
    }

}
