package com.ledwon.jakub.githubapiclient.repository;

import android.util.Log;

import com.ledwon.jakub.githubapiclient.repository.data.ListOfReposResponse;
import com.ledwon.jakub.githubapiclient.repository.data.Repo;
import com.ledwon.jakub.githubapiclient.repository.data.RepoResponse;

import java.util.List;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class GitHubRepository {
    private static final String TAG = "GitHubRepository";


    private GitHubApi mGitHubApi;

    public GitHubRepository(){
        mGitHubApi = GitHubRetrofit.getGitHubApi();
    }

    public LiveData<ListOfReposResponse> getListOfRepos(final String username) {
        Call<List<Repo>> call = mGitHubApi.getListOfRepos(username);

        final MutableLiveData<ListOfReposResponse> data = new MutableLiveData<>();

        call.enqueue(new Callback<List<Repo>>() {
            @Override
            public void onResponse(Call<List<Repo>> call, Response<List<Repo>> response) {
                if (response.isSuccessful()) {
                    Log.d(TAG, "onResponse getListOfRepos code :" + response.code());

                    data.postValue(new ListOfReposResponse(response.body(), response.code()));

                    for (Repo repo : response.body()) {
                        Log.d(TAG, "onResponse getListOfRepos: " + repo.toString());
                    }

                } else
                    data.postValue(new ListOfReposResponse(null, response.code()));
            }

            @Override
            public void onFailure(Call<List<Repo>> call, Throwable t) {
                Log.d(TAG, "onFailure getListOfRepos message: " + t.getMessage());
                data.postValue(new ListOfReposResponse(t));
            }
        });
        return data;
    }

    public LiveData<RepoResponse> getRepo(String username, String repo) {
        Call<Repo> call = mGitHubApi.getRepo(username, repo);

        final MutableLiveData<RepoResponse> data = new MutableLiveData<>();

        call.enqueue(new Callback<Repo>() {
            @Override
            public void onResponse(Call<Repo> call, Response<Repo> response) {
                Log.d(TAG, "onResponse getRepo code : " + response.code());
                if (response.isSuccessful()) {
                    data.postValue(new RepoResponse(response.body(), response.code()));
                    Log.d(TAG, "onResponse getRepo : " + response.body().toString());
                } else
                    data.postValue(new RepoResponse(null, response.code()));
            }
            @Override
            public void onFailure(Call<Repo> call, Throwable t) {
                Log.d(TAG, "onFailure getRepo message: " + t.getMessage());
                data.setValue(new RepoResponse(t));
            }
        });
        return data;
    }
}
