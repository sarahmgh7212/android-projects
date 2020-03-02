package com.sarah.assignment2_s3667123.model.viewmodel;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;

import com.sarah.assignment2_s3667123.model.Event;
import com.sarah.assignment2_s3667123.model.FileModel;
import com.sarah.assignment2_s3667123.model.FileModelImplement;


import java.util.Map;


public class ItemsListViewModel extends AndroidViewModel {
    // Maintain Event Live data till change the event details

    private MutableLiveData<Map<String, Event>> mapMutableLiveData;

    public ItemsListViewModel(Application application) {
        super(application);
    }

    public LiveData<Map<String, Event>> getItems() {
        // Get Event Details from Event
        if(mapMutableLiveData == null) {
            mapMutableLiveData = new MutableLiveData<>();
            FileModel model = FileModelImplement.getSingletonInstance(getApplication());
            mapMutableLiveData.setValue(model.getEventList());
        }
        return mapMutableLiveData;
    }
}
