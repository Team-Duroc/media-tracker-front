package com.northcoders.media_tracker_front.model;

import android.app.Application;
import android.util.Log;

import androidx.lifecycle.MutableLiveData;

import com.northcoders.media_tracker_front.service.MovieApiService;
import com.northcoders.media_tracker_front.service.RetrofitInstance;
import com.northcoders.media_tracker_front.service.UserActionsService;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MovieDetailsRepository {

    MutableLiveData<FilmDetails> mutableLiveData = new MutableLiveData<>();

    private final Application application;

    public MovieDetailsRepository(Application application) {
        this.application = application;
    }

    public MutableLiveData<FilmDetails> getMutableLiveData(Long id) {
        MovieApiService movieApiService = RetrofitInstance.getService();

        Call<FilmDetails> call = movieApiService.getFilmDetails(id);
        call.enqueue(new Callback<FilmDetails>() {
            @Override
            public void onResponse(Call<FilmDetails> call, Response<FilmDetails> response) {
                Log.i("MovieDetailsRepo",String.valueOf(response.code()));
                Log.i("MovieDetailsRepo", response.body().toString());
                FilmDetails film = response.body();
                mutableLiveData.setValue(film);
            }

            @Override
            public void onFailure(Call<FilmDetails> call, Throwable t) {
                Log.i("GET request", t.getMessage());

            }
        });

        return mutableLiveData;
    }

    public void saveUserFilm(long id, Bookmarked bookmarked){
        UserActionsService service = RetrofitInstance.getUserService();
        Call<Void> call = service.saveUserFilm(id, bookmarked);
        call.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                Log.i("Response From Movie Details Repo", String.valueOf(response.code()));
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {

            }
        });





    }



}
