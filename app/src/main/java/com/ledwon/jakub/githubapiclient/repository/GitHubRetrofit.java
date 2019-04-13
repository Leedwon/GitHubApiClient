package com.ledwon.jakub.githubapiclient.repository;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class GitHubRetrofit {
    public static final String BASE_URL = "https://api.github.com/";

    private static Retrofit getRetrofitInstance() {
        return new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    public static GitHubApi getGitHubApi(){
        return getRetrofitInstance().create(GitHubApi.class);
    }

}
