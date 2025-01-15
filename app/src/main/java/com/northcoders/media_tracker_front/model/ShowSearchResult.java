package com.northcoders.media_tracker_front.model;

public class ShowSearchResult {
    private Long id;
    private String name;
    private String overview;
    private String poster_path;
    private String first_air_date;


    public ShowSearchResult() {
    }

    public ShowSearchResult(Long id, String name, String overview, String poster_path, String first_air_date) {
        this.id = id;
        this.name = name;
        this.overview = overview;
        this.poster_path = poster_path;
        this.first_air_date = first_air_date;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getOverview() {
        return overview;
    }

    public void setOverview(String overview) {
        this.overview = overview;
    }

    public String getPoster_path() {
        return poster_path;
    }

    public void setPoster_path(String poster_path) {
        this.poster_path = poster_path;
    }

    public String getFirst_air_date() {
        return first_air_date;
    }

    public void setFirst_air_date(String first_air_date) {
        this.first_air_date = first_air_date;
    }
}

