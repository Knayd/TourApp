package com.example.applaudo.tourguideapp;

import android.arch.lifecycle.ViewModel;

import com.example.applaudo.tourguideapp.room.repository.PlaceRepository;

public class VisitLaterViewModel extends ViewModel {

    private PlaceRepository repository;

    public VisitLaterViewModel() {
        repository = new PlaceRepository();
    }

    public PlaceRepository getRepository() {
        return repository;
    }
}
