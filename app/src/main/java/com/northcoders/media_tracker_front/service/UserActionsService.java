package com.northcoders.media_tracker_front.service;

import com.northcoders.media_tracker_front.model.Bookmarked;
import com.northcoders.media_tracker_front.model.WatchHistory;

import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.PATCH;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface UserActionsService {

    @GET("users/auth")
    Call<Void> auth();

    @GET("users/films/search")
    Call<List<WatchHistory>> getHistory(@Query("status") String status);

    @GET("users/films/search?status=BOOKMARKED")
    Call<List<Bookmarked>> getBookmarked();

    @GET("users/films/{filmId}")
    Call<Bookmarked> getBookmarkedFilm(@Path("filmId") Long filmId);

    @DELETE("users")
    Call<Void> deleteUser();

    @GET("users/films/search?status=WATCHED")
    Call<List<WatchHistory>> getHistory();
    @GET("users/films/{filmDbId}")
    Call<WatchHistory> getUserFilmDetails(@Path("filmDbId") Long movieId );


    @PATCH("users/films/{filmDbId}")
    Call<Void> updateUserFilm(@Path("filmDbId") Long id, @Body WatchHistory film);

    @PATCH("users/films/{filmDbId}")
    Call<Void> updateUserBookFilm(@Path("filmDbId") Long id, @Body Bookmarked film);


    @GET("users/genreStats")
    Call<Map<String,Integer>> getUserGenreStats();

    @GET("users/totalWatchedRuntime")
    Call<Integer> getTotalWatchedRuntime();

    @POST("users/films/{movieid}")
    Call<Void> saveUserFilm(@Path ("movieid") Long id, @Body Bookmarked bookmarked);



}
