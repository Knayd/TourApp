package com.example.applaudo.tourguideapp;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class Places1Fragment extends Fragment implements PlacesAdapter.OnItemClicked {


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_places,container,false);

        ArrayList<Place> placesList= new ArrayList<>();

        TextView tv = v.findViewById(R.id.tv_text);
        RecyclerView rv = v.findViewById(R.id.rv_fragments);

        LinearLayoutManager llm = new LinearLayoutManager(getContext());

        placesList.add(new Place(R.drawable.ic_launcher_foreground,"Test","Test","Test","12345678"));
        placesList.add(new Place(R.drawable.ic_launcher_background,"Test1","Test1","Test1","123456789"));
        placesList.add(new Place(R.drawable.ic_launcher_foreground,"Test2","Test2","Test2","123456789"));
        placesList.add(new Place(R.drawable.ic_launcher_background,"Test3","Test3","Test3","123456789"));
        placesList.add(new Place(R.drawable.ic_launcher_foreground,"Test4","Test4","Test4","123456789"));
        placesList.add(new Place(R.drawable.ic_launcher_background,"Test5","Test5","Test5","123456789"));
        placesList.add(new Place(R.drawable.ic_launcher_foreground,"Test6","Test6","Test6","123456789"));
        placesList.add(new Place(R.drawable.ic_launcher_background,"Test7","Test7","Test7","123456789"));
        placesList.add(new Place(R.drawable.ic_launcher_foreground,"Test8","Test8","Test8","123456789"));

        PlacesAdapter adapter = new PlacesAdapter(placesList, this);

        rv.setLayoutManager(llm);
        rv.setAdapter(adapter);

        return v;

    }

    //Implementation of the interface
    @Override
    public void onItemClicked(int position, String name, String description, String website, String tel, int img) {
        Toast.makeText(getContext(),String.valueOf(position),Toast.LENGTH_LONG).show();

        Intent intent = DetailsActivity.getInstance(getContext(),name,description,website,tel,img);
        startActivity(intent);

    }

}
