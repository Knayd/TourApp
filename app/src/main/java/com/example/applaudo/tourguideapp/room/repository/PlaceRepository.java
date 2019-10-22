package com.example.applaudo.tourguideapp.room.repository;

import android.arch.lifecycle.LiveData;
import android.os.AsyncTask;

import com.example.applaudo.tourguideapp.TourApp;
import com.example.applaudo.tourguideapp.model.Place;
import com.example.applaudo.tourguideapp.room.dao.PlaceDao;
import com.example.applaudo.tourguideapp.room.db.PlacesDatabase;

import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class PlaceRepository {
    private PlaceDao placeDao;
    private LiveData<List<Place>> allPlaces;
    private Executor executor;

    public PlaceRepository() {
        PlacesDatabase database = PlacesDatabase.getDatabase(TourApp.getContext());
        placeDao = database.placeDao();
        allPlaces = placeDao.getPlacesToVisit();
        executor = Executors.newSingleThreadExecutor();
    }

    public LiveData<List<Place>> getAllPlaces() {
        return allPlaces;
    }

    public void insert(final Place place) {
        executor.execute(
                new Runnable() {
                    @Override
                    public void run() {
                        placeDao.insert(place);
                    }
                }
        );
        //new DatabaseAsyncTask(placeDao).execute(place);
    }

    public void delete(final Place place) {
        executor.execute(
                new Runnable() {
                    @Override
                    public void run() {
                        placeDao.delete(place);
                    }
                }
        );
    }
}
