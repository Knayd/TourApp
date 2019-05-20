package com.example.applaudo.tourguideapp.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.applaudo.tourguideapp.TourApp;
import com.example.applaudo.tourguideapp.network.TourApi;
import com.example.applaudo.tourguideapp.util.DetailActions;
import com.example.applaudo.tourguideapp.model.Place;
import com.example.applaudo.tourguideapp.adapter.PlacesAdapter;
import com.example.applaudo.tourguideapp.R;
import com.example.applaudo.tourguideapp.activities.DetailsActivity;

import java.util.ArrayList;
import java.util.List;

import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class PlaceFragment extends Fragment implements PlacesAdapter.OnItemClicked {

    PlacesAdapter adapter;
    RecyclerView recyclerView;
    TourApi tourApi = TourApp.getTourApi();
    public static final String ARG_CATEGORY_ID = "ARG_CATEGORY_ID";

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        ArrayList<Place> placesList = new ArrayList<>();

        View view = inflater.inflate(R.layout.fragment_places, container, false);

        recyclerView = view.findViewById(R.id.rv_fragments);

        //This is where I retrieve the tab number
        Bundle arguments = getArguments();
        String id = arguments.getString(ARG_CATEGORY_ID);

        tourApi.getPlaces(id).enqueue(new Callback<List<Place>>() {
            @Override
            public void onResponse(Call<List<Place>> call, Response<List<Place>> response) {
                setPlaces(response.body());
            }

            @Override
            public void onFailure(Call<List<Place>> call, Throwable t) {
                Toast.makeText(getContext(), "Failure", Toast.LENGTH_LONG).show();
            }
        });

        prepareRecycler();
        setPlaces(placesList);

        return view;
    }

    private void setPlaces(List<Place> places){
        if(adapter == null){
            adapter = new PlacesAdapter(places, this);
            recyclerView.setAdapter(adapter);
        } else {
            adapter.updatePlaces(places);
        }
    }

    private void prepareRecycler(){
        LinearLayoutManager manager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(manager);
    }

    //Implementation of the interface
    @Override
    public void onItemClicked(Place place) {
        Intent intent = new Intent(getContext(), DetailsActivity.class);
        intent.putExtra(DetailsActivity.EXTRA_PLACE, place);
        intent.putExtra(DetailsActivity.EXTRA_ACTION, DetailActions.ACTION_ADD);
        startActivity(intent);
    }

}
