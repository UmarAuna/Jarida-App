package com.example.jaridaapp.jaridaV2.networking;

import com.example.jaridaapp.jaridaV1.model.JaridaListItem;
import com.example.jaridaapp.jaridaV2.model.ContentItem;
import com.example.jaridaapp.jaridaV2.model.User;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface ApiService {

    @GET("api/v2/posts")
    Call<User> getUsers(@Query("page") long page
                       /*,@Query("sort") String title*/);

    @FormUrlEncoded
    @POST("api/v2/posts")
    Call<ContentItem> postJarida(@Field("title")String title,
                                 @Field("content") String content,
                                 @Field("author") String author);


}
