package com.example.applaudo.tourguideapp.room.db;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;

import com.example.applaudo.tourguideapp.model.Place;
import com.example.applaudo.tourguideapp.room.dao.PlaceDao;

@Database(entities = {Place.class}, version = 1)
public abstract class PlacesDatabase extends RoomDatabase {
    public abstract PlaceDao placeDao();

    private static PlacesDatabase databseInstance;

    public static PlacesDatabase getDatabase(Context context) {
        if (databseInstance == null) {
            synchronized (PlacesDatabase.class) {
                databseInstance = Room.databaseBuilder(context.getApplicationContext(),
                        PlacesDatabase.class,
                        "places_database")
                        .build();
            }
        }

        return databseInstance;
    }
}
