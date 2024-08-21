package com.example.games.data;

import com.example.games.model.RemoteResult
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Header
import retrofit2.http.Query;

interface RetrofitService {

    @GET("movie/now_playing")
    suspend fun listPopularMovies(
        @Query("language") language: String,
        @Header("Authorization") apiKey: String,
        @Header("accept") accept: String,
    ): RemoteResult
}
