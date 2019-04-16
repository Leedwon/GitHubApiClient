package com.ledwon.jakub.githubapiclient.ui.viewmodels;

import com.ledwon.jakub.githubapiclient.data.GitHubRepository;
import com.ledwon.jakub.githubapiclient.data.responses.ListOfReposResponse;

import javax.inject.Inject;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

public class ShowReposActivityViewModel extends ViewModel {

    private GitHubRepository mGitHubRepository;

    @Inject
    public ShowReposActivityViewModel(GitHubRepository gitHubRepository) {
        this.mGitHubRepository = gitHubRepository;
    }

    public LiveData<ListOfReposResponse> getListOfRepos(String username) {return mGitHubRepository.getListOfRepos(username);}
}
