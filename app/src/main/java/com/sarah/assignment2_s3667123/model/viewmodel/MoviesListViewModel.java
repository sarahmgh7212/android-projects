package com.sarah.assignment2_s3667123.model.viewmodel;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.util.Log;

import com.sarah.assignment2_s3667123.model.FileModel;
import com.sarah.assignment2_s3667123.model.FileModelImplement;
import com.sarah.assignment2_s3667123.model.Movie;


import java.util.Collection;
import java.util.Map;

public class MoviesListViewModel  extends AndroidViewModel{
    // Maintain Movie Live data till change the event details
    private MutableLiveData<Map<String, Movie>> itemsLiveData;

    public MoviesListViewModel(Application application) {
        super(application);
    }
    public LiveData<Map<String, Movie>> getMovies() {
        // Get Event Details from Movie
        if(itemsLiveData == null) {
            itemsLiveData = new MutableLiveData<>();
            FileModel model = FileModelImplement.getSingletonInstance(getApplication());
            itemsLiveData.setValue(model.getMovieList());
        }
        Collection<Movie> movies = (Collection<Movie>) itemsLiveData.getValue().values();
        for (Movie movie: movies  ){
            Log.i("MoviesListViewModel", movie.getMovieTitle());
        }

        return itemsLiveData;
    }
}
