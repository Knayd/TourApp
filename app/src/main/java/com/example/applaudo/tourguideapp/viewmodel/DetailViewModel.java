package com.example.applaudo.tourguideapp.viewmodel;

import android.arch.lifecycle.ViewModel;

import com.example.applaudo.tourguideapp.model.Place;
import com.example.applaudo.tourguideapp.room.repository.PlaceRepository;

public class DetailViewModel extends ViewModel {

    private PlaceRepository repository;

    public DetailViewModel(){
        this.repository = new PlaceRepository();
    }

    public void addPlace(Place place){
        repository.insert(place);
    }

    public void deletePlace(Place place){
        repository.delete(place);
    }
}
