package com.example.api;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface jsonApi {

    @GET("users")
    Call<PostModal> postlist(@Query("delay") int delay);

}
