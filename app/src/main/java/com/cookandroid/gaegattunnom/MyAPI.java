package com.cookandroid.gaegattunnom;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.PATCH;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface MyAPI{

    @Headers("password: 1111")

    @POST("/v1/account")
    Call<PostItem> post_posts(@Body PostItem post);

    @PATCH("v1/account/{accountId}")
    Call<PostItem> patch_posts(@Path("pk") int pk, @Body PostItem post);

    @DELETE("/v1/account/{accountId}")
    Call<PostItem> delete_posts(@Path("pk") int pk);

    @GET("/v1/account/{accountId}")
    Call<List<PostItem>> get_posts(@Path("accountId") int accountId);

   // @GET("/v1/account/{accountId}/")
    //Call<PostItem> get_post_pk(@Path("pk") String );
}
