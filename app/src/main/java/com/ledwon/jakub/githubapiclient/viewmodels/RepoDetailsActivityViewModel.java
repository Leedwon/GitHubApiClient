package com.ledwon.jakub.githubapiclient.viewmodels;

import com.ledwon.jakub.githubapiclient.repository.GitHubRepository;
import com.ledwon.jakub.githubapiclient.repository.data.RepoResponse;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

public class RepoDetailsActivityViewModel extends ViewModel {
    private GitHubRepository mGitHubRepository;

    public RepoDetailsActivityViewModel() {
        mGitHubRepository = new GitHubRepository();
    }

    public LiveData<RepoResponse> getRepo(String username, String repo) { return mGitHubRepository.getRepo(username, repo);}
}
