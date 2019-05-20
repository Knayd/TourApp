package com.example.applaudo.tourguideapp.model;

import com.google.gson.annotations.SerializedName;

public class Category {
    @SerializedName("idCateg")
    private String id;
    @SerializedName("nombre")
    private String name;

    public Category(String id, String name) {
        this.id = id;
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
