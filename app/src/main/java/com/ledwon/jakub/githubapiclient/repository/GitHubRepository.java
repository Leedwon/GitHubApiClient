package com.ledwon.jakub.githubapiclient.repository;

import android.util.Log;

import com.ledwon.jakub.githubapiclient.repository.data.Repo;

import java.util.List;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class GitHubRepository {
    private static final String TAG = "GitHubRepository";

    public GitHubRepository() {

    }

    public LiveData<List<Repo>> getListRepos(final String username) {
        Call<List<Repo>> call = GitHubRetrofit.getGitHubApi().getListRepos(username);

        final MutableLiveData<List<Repo>> data = new MutableLiveData<>();

        call.enqueue(new Callback<List<Repo>>() {
            @Override
            public void onResponse
                    (Call<List<Repo>> call, Response<List<Repo>> response) {
                if (response.isSuccessful()) {
                    data.postValue(response.body());
                    for (Repo repo : response.body()) {
                        Log.d(TAG, "onResponse: " + repo.toString());
                    }
                } else if (response.code() == 404) {
                    data.setValue(null);
                }
            }

            @Override
            public void onFailure(Call<List<Repo>> call, Throwable t) {
                data.setValue(null);
                Log.d(TAG, "onFailure: ");
            }
        });

        return data;
    }

    public LiveData<Repo> getRepo(String username, String repo) {
        Call<Repo> call = GitHubRetrofit.getGitHubApi().getRepo(username, repo);

        final MutableLiveData<Repo> data = new MutableLiveData<>();

        call.enqueue(new Callback<Repo>() {
            @Override
            public void onResponse(Call<Repo> call, Response<Repo> response) {
                if (response.isSuccessful()) {
                    data.postValue(response.body());
                    Log.d(TAG, "onResponse: " + response.body().toString());
                }
            }

            @Override
            public void onFailure(Call<Repo> call, Throwable t) {
                data.setValue(null);
            }
        });
        return data;
    }
}
