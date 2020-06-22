package com.example.jaridaapp.jaridaV1.utils;

import com.example.jaridaapp.jaridaV1.model.JaridaListItem;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.DELETE;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface ApiService {
    @GET("api/v1/jarida")
    Call <List<JaridaListItem>> getJarida();

    @FormUrlEncoded
    @POST("api/v1/jarida")
    Call<JaridaListItem> postJarida(@Field("title")String title,
                                     @Field("content") String content,
                                     @Field("author") String author);

    @DELETE("api/v1/jarida/{id}")
    Call<JaridaListItem> deleteJarida(@Path("id") int post_id);

    @FormUrlEncoded
    @PUT("api/v1/jarida/{id}")
    Call<JaridaListItem> updateJarida(@Path("id") int id,
                                      @Field("title")String title,
                                      @Field("content") String content,
                                      @Field("author") String author);
}
