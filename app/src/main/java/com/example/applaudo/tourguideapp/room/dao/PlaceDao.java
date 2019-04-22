package com.example.applaudo.tourguideapp.room.dao;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import com.example.applaudo.tourguideapp.model.Place;

import java.util.List;

@Dao
public interface PlaceDao {

    @Insert
    void insert(Place place);

    @Query("DELETE FROM places_table")
    void deleteAll();

    @Query("SELECT * from places_table")
    LiveData<List<Place>> getPlacesToVisit();
}
