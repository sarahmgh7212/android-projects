package com.sarah.assignment2_s3667123.model;

public abstract class AbstractMovie implements Movie{
    // Create Abstract Movie class
    private String movieId;
    private String movieTitle;
    private String movieYear;
    private String poster;

    //    "CREATE TABLE IF NOT EXISTS events (\n" +
//            "    movieid varchar(200) NOT NULL CONSTRAINT employees_pk PRIMARY KEY ,\n" +
//            "    movietitle varchar(200) NOT NULL,\n" +
//            "    year varchar(200) NOT NULL,\n" +
//            "    poster varchar(200) NOT NULL\n" +
//            ");"

    protected AbstractMovie(String movieId,String movieTitle,String movieYear,String poster){
        //Constructor for Movie Class
        this.movieId = movieId;
        this.movieTitle = movieTitle;
        this.movieYear = movieYear;
        this.poster = poster;
        }

    protected AbstractMovie(String movieId){
        //Constructor for Movie Class
        this.movieId = movieId;

    }

    public String getMovieId(){
        return movieId;
    }

    public String getMovieTitle(){
        return movieTitle;
    }

    public String movieYear(){
        return movieYear;
    }

    public String poster(){
        return poster;
    }

    @Override
    public String toString() {
        return String.format("Id = %s, title = %s", movieId, movieTitle);
    }
}
