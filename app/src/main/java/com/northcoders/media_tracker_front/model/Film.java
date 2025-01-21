package com.northcoders.media_tracker_front.model;

import androidx.databinding.Bindable;

import java.util.List;

public class Film {

    //TODO: datatype for genres?
    private Long id;
    private String title;
    private String synopsis;
    private String director;
    private String country;
    private String language;
    //duration in minutes
    private int duration;
    private int releaseYear;
    private List<String> genres;

    public Film() {
    }

    public Film(Long id, String title, String synopsis, String director, String country, String language, int duration, int releaseYear, List<String> genres) {
        this.id = id;
        this.title = title;
        this.synopsis = synopsis;
        this.director = director;
        this.country = country;
        this.language = language;
        this.duration = duration;
        this.releaseYear = releaseYear;
        this.genres = genres;
    }

    @Bindable
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Bindable
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Bindable
    public String getSynopsis() {
        return synopsis;
    }

    public void setSynopsis(String synopsis) {
        this.synopsis = synopsis;
    }

    @Bindable
    public String getDirector() {
        return director;
    }

    public void setDirector(String director) {
        this.director = director;
    }

    @Bindable
    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    @Bindable
    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    @Bindable
    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    @Bindable
    public int getReleaseYear() {
        return releaseYear;
    }

    public void setReleaseYear(int releaseYear) {
        this.releaseYear = releaseYear;
    }

    @Bindable
    public List<String> getGenres() {
        return genres;
    }

    public void setGenres(List<String> genres) {
        this.genres = genres;
    }
}

