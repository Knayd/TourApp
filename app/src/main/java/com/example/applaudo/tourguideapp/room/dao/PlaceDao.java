package com.example.applaudo.tourguideapp.room.dao;

import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import com.example.applaudo.tourguideapp.model.Place;

import java.util.List;

public interface PlaceDao {

    @Insert
    void insert(Place place);

    @Query("DELETE FROM places_table")
    void deleteAll();

    @Query("SELECT * from places_table")
    List<Place> getPlacesToVisit();
}
