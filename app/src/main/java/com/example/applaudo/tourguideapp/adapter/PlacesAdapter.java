package com.example.applaudo.tourguideapp.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.applaudo.tourguideapp.GlideApp;
import com.example.applaudo.tourguideapp.R;
import com.example.applaudo.tourguideapp.model.Place;

import java.util.List;

public class PlacesAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {


    private List<Place> mPlaceList;
    private OnItemClicked mCallback;

    public PlacesAdapter(List<Place> placeList, OnItemClicked mCallback) {
        this.mPlaceList = placeList;
        this.mCallback = mCallback;
    }

    public void updatePlaces(List<Place> newPlaces){
        mPlaceList = newPlaces;
        notifyDataSetChanged();
    }

    public interface OnItemClicked {
        void onItemClicked(Place place);
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        //Inflates de view
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_places, parent, false);
        return new PlacesViewHolder(v);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        PlacesViewHolder pv = (PlacesViewHolder) holder;
        //Sets the values to the VH
        pv.bindView(mPlaceList, position);
    }

    @Override
    public int getItemCount() {
        return mPlaceList.size();
    }

    class PlacesViewHolder extends RecyclerView.ViewHolder {

        private final TextView mItemName, mItemDescription;
        private final ImageView mItemImg;
        private final View mLayout;

        PlacesViewHolder(View itemView) {
            super(itemView);
            mItemName = itemView.findViewById(R.id.item_name);
            mItemDescription = itemView.findViewById(R.id.item_description);
            mLayout = itemView.findViewById(R.id.item_container);

            mItemImg = itemView.findViewById(R.id.item_img);

        }

        //Sets the data to the view
        void bindView(final List<Place> list, final int position) {
            mItemName.setText(list.get(position).getName());
            mItemDescription.setText(list.get(position).getDescription());

            GlideApp.with(mItemImg.getContext())
                    .load(list.get(position).getImgSrc())
                    .into(mItemImg);

            //Listens when an item is clicked, and executes the interface's method
            mLayout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mCallback.onItemClicked(list.get(position));
                }
            });
        }
    }


}
