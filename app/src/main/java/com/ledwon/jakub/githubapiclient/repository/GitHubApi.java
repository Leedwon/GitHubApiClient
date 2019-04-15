package com.ledwon.jakub.githubapiclient.repository;

import com.ledwon.jakub.githubapiclient.repository.data.Repo;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface GitHubApi {

    @GET("users/{username}/repos")
    Call<List<Repo>> getListOfRepos(@Path("username") String username);

    @GET("repos/{username}/{repo}")
    Call<Repo> getRepo(@Path("username") String username, @Path("repo") String repo);
}
