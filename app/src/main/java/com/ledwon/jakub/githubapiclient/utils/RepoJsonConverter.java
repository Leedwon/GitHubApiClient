package com.ledwon.jakub.githubapiclient.utils;

import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.ledwon.jakub.githubapiclient.repository.data.Repo;

public class RepoJsonConverter {
    private static final String TAG = "RepoJsonConverter";

    public static String toJsonString(Repo repo){
        Gson gson = new GsonBuilder().create();
        Log.d(TAG, "toJsonString: " + gson.toJson(repo));
        return gson.toJson(repo);
    }

    public static Repo toRepo(String repoJson){
        Gson gson = new GsonBuilder().create();
        Log.d(TAG, "toRepo: " + gson.fromJson(repoJson, Repo.class).toString());
        return gson.fromJson(repoJson, Repo.class);
    }
}
