package com.ledwon.jakub.githubapiclient.data;

import android.util.Log;

import com.ledwon.jakub.githubapiclient.data.responses.ListOfReposResponse;
import com.ledwon.jakub.githubapiclient.data.model.Repo;
import com.ledwon.jakub.githubapiclient.data.responses.RepoResponse;

import java.util.List;

import javax.inject.Singleton;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

@Singleton
public class GitHubRepository {
    private GitHubApi mGitHubApi;

    public GitHubRepository(GitHubApi gitHubApi) {
        this.mGitHubApi = gitHubApi;
    }

    public LiveData<ListOfReposResponse> getListOfRepos(final String username) {
        Call<List<Repo>> call = mGitHubApi.getListOfRepos(username);

        final MutableLiveData<ListOfReposResponse> data = new MutableLiveData<>();

        call.enqueue(new Callback<List<Repo>>() {
            @Override
            public void onResponse(Call<List<Repo>> call, Response<List<Repo>> response) {
                if (response.isSuccessful()) {
                    data.postValue(new ListOfReposResponse(response.body(), response.code()));
                } else
                    data.postValue(new ListOfReposResponse(null, response.code()));
            }

            @Override
            public void onFailure(Call<List<Repo>> call, Throwable t) {
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
                if (response.isSuccessful())
                    data.postValue(new RepoResponse(response.body(), response.code()));
                else
                    data.postValue(new RepoResponse(null, response.code()));
            }

            @Override
            public void onFailure(Call<Repo> call, Throwable t) {
                data.setValue(new RepoResponse(t));
            }
        });
        return data;
    }
}
