package com.example.applaudo.tourguideapp.network;

import com.example.applaudo.tourguideapp.model.Category;
import com.example.applaudo.tourguideapp.model.Place;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface TourApi {

    String BASE_URL = "http://turistapp.azurewebsites.net/";


    @GET("Lugares")
    Call<List<Place>> getPlaces(@Query("idcateg") String id);

    @GET("Categorias")
    Call<List<Category>> getCategories();

}
