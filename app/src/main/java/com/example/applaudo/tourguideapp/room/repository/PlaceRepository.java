package com.example.applaudo.tourguideapp.room.repository;

import android.arch.lifecycle.LiveData;
import android.os.AsyncTask;

import com.example.applaudo.tourguideapp.TourApp;
import com.example.applaudo.tourguideapp.model.Place;
import com.example.applaudo.tourguideapp.room.dao.PlaceDao;
import com.example.applaudo.tourguideapp.room.db.PlacesDatabase;

import java.util.List;

public class PlaceRepository {
    private PlaceDao placeDao;
    private LiveData<List<Place>> allPlaces;

    public PlaceRepository() {
        PlacesDatabase database = PlacesDatabase.getDatabase(TourApp.getContext());
        placeDao = database.placeDao();
        allPlaces = placeDao.getPlacesToVisit();
    }

    public LiveData<List<Place>> getAllPlaces() {
        return allPlaces;
    }

    public void insert(Place place) {
        new InsertAsyncTask(placeDao).execute(place);
    }

    private static class InsertAsyncTask extends AsyncTask<Place, Void, Void> {

        private PlaceDao asyncTaskPlaceDao;

        InsertAsyncTask(PlaceDao asyncTaskPlaceDao) {
            this.asyncTaskPlaceDao = asyncTaskPlaceDao;
        }

        @Override
        protected Void doInBackground(Place... places) {
            asyncTaskPlaceDao.insert(places[0]);
            return null;
        }
    }
}
