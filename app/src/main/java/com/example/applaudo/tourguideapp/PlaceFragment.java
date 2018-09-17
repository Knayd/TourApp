package com.example.applaudo.tourguideapp;

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

import java.util.ArrayList;

public class PlaceFragment extends Fragment implements PlacesAdapter.OnItemClicked {


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        ArrayList<Place> placesList= new ArrayList<>();

        View v = inflater.inflate(R.layout.fragment_places,container,false);

        RecyclerView rv = v.findViewById(R.id.rv_fragments);

        LinearLayoutManager llm = new LinearLayoutManager(getContext());

        //This is where I retrieve the tab number
        Bundle getTabData = getArguments();
        if (getTabData != null) {
            int mLoadType = getTabData.getInt("tabnumber");
            if (mLoadType ==0){
                //TAB 1
                placesList.add(new Place(R.drawable.ic_launcher_foreground,"Tab1","Tab1","www.facebook.com","12345678"));

            } else if(mLoadType ==1) {
                //TAB 2
                placesList.add(new Place(R.drawable.ic_launcher_background,"Tab2","Tab2","www.youtube.com","123456789"));
            } else if(mLoadType ==2) {
                //TAB 3
                placesList.add(new Place(R.drawable.ic_launcher_background,"Tab3","Tab3","stackoverflow.com","123456789"));
            } else {
                //TAB 4
                placesList.add(new Place(R.drawable.ic_launcher_background,"Tab4","Tab4","developer.android.com","123456789"));
            }
        }

//        placesList.add(new Place(R.drawable.ic_launcher_foreground,"Test","Test","Test","12345678"));
//        placesList.add(new Place(R.drawable.ic_launcher_background,"Test1","Test1","Test1","123456789"));
//        placesList.add(new Place(R.drawable.ic_launcher_foreground,"Test2","Test2","Test2","123456789"));
//        placesList.add(new Place(R.drawable.ic_launcher_background,"Test3","Test3","Test3","123456789"));
//        placesList.add(new Place(R.drawable.ic_launcher_foreground,"Test4","Test4","Test4","123456789"));
//        placesList.add(new Place(R.drawable.ic_launcher_background,"Test5","Test5","Test5","123456789"));
//        placesList.add(new Place(R.drawable.ic_launcher_foreground,"Test6","Test6","Test6","123456789"));
//        placesList.add(new Place(R.drawable.ic_launcher_background,"Test7","Test7","Test7","123456789"));
//        placesList.add(new Place(R.drawable.ic_launcher_foreground,"Test8","Test8","Test8","123456789"));

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
