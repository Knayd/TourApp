package com.example.applaudo.tourguideapp.viewmodel;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.ViewModel;

import com.example.applaudo.tourguideapp.model.Place;
import com.example.applaudo.tourguideapp.room.repository.PlaceRepository;

import java.util.List;

public class VisitLaterViewModel extends ViewModel {

    private PlaceRepository repository;

    public VisitLaterViewModel() {
        repository = new PlaceRepository();
    }

    public LiveData<List<Place>> getPlaces(){
       return repository.getAllPlaces();
    }
}
