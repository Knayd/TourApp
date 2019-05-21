package com.example.applaudo.tourguideapp.model;

import com.google.gson.annotations.SerializedName;

public class User {
    @SerializedName("idUser")
    private String idUser;
    @SerializedName("username")
    private String userName;
    @SerializedName("idRol")
    private String idRole;
    @SerializedName("rol")
    private String role;

    public User(String idUser, String userName, String idRole, String role) {
        this.idUser = idUser;
        this.userName = userName;
        this.idRole = idRole;
        this.role = role;
    }

    public String getIdUser() {
        return idUser;
    }

    public void setIdUser(String idUser) {
        this.idUser = idUser;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getIdRole() {
        return idRole;
    }

    public void setIdRole(String idRole) {
        this.idRole = idRole;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}
