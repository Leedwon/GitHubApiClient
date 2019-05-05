package com.ledwon.jakub.githubapiclient.data;

import com.ledwon.jakub.githubapiclient.data.model.Repo;

import java.util.List;

import javax.inject.Singleton;

import io.reactivex.Single;

@Singleton
public class GitHubRepository {
    private GitHubApi mGitHubApi;

    public GitHubRepository(GitHubApi gitHubApi) {
        this.mGitHubApi = gitHubApi;
    }

    public Single<List<Repo>> getListOfRepos(String username){
        return mGitHubApi.getListOfRepos(username);
    }

    public Single<Repo> getRepo(String username, String repoName) {
        return mGitHubApi.getRepo(username, repoName);
    }
}
