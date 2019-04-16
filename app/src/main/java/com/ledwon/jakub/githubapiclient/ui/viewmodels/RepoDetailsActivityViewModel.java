package com.ledwon.jakub.githubapiclient.ui.viewmodels;

import com.ledwon.jakub.githubapiclient.data.GitHubRepository;
import com.ledwon.jakub.githubapiclient.data.responses.RepoResponse;

import javax.inject.Inject;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

public class RepoDetailsActivityViewModel extends ViewModel {

    private GitHubRepository mGitHubRepository;

    @Inject
    public RepoDetailsActivityViewModel(GitHubRepository gitHubRepository) {
        this.mGitHubRepository = gitHubRepository;
    }

    public LiveData<RepoResponse> getRepo(String username, String repo) { return mGitHubRepository.getRepo(username, repo);}
}
