package com.ledwon.jakub.githubapiclient.data;

import com.ledwon.jakub.githubapiclient.data.model.Repo;

import java.util.List;

import io.reactivex.Single;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface GitHubApi {

    @GET("users/{username}/repos")
    Single<List<Repo>> getListOfRepos(@Path("username") String username);

    @GET("repos/{username}/{repo}")
    Single<Repo> getRepo(@Path("username") String username, @Path("repo") String repo);
}
