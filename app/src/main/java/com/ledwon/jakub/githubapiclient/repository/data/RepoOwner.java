package com.ledwon.jakub.githubapiclient.repository.data;

import com.google.gson.annotations.SerializedName;

import androidx.annotation.NonNull;

public class RepoOwner {
    @SerializedName("login")
    private String mLogin;

    public String getLogin() {
        return mLogin;
    }

    @NonNull
    @Override
    public String toString() {
        return "login: " + mLogin;
    }
}
