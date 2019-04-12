package com.ledwon.jakub.githubapiclient.repository;

import android.util.Log;

import com.ledwon.jakub.githubapiclient.repository.data.Repo;

import java.util.ArrayList;
import java.util.List;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class GitHubRepository {
    private static final String TAG = "GitHubRepository";

    public static final String BASE_URL = "https://api.github.com/";

    private Retrofit mRetrofit;

    private GitHubApi mGithubApi;

    public GitHubRepository(){
        mRetrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        mGithubApi = mRetrofit.create(GitHubApi.class);
    }

    public LiveData<List<Repo>> getRepos(String username){
        Call<List<Repo>> call = mGithubApi.listRepos(username);

        final MutableLiveData<List<Repo>> data = new MutableLiveData<>();

        call.enqueue(new Callback<List<Repo>>() {
            @Override
            public void onResponse(Call<List<Repo>> call, Response<List<Repo>> response) {
                if(response.isSuccessful()){
                    data.postValue(response.body());
                    for (Repo repo : response.body()){
                        Log.d(TAG, "onResponse: " + repo.toString());
                    }
                }
            }

            @Override
            public void onFailure(Call<List<Repo>> call, Throwable t) {
                data.setValue(null);
            }
        });

        return data;
    }
}
