package com.ledwon.jakub.githubapiclient.viewmodels;

import com.ledwon.jakub.githubapiclient.repository.GitHubRepository;
import com.ledwon.jakub.githubapiclient.repository.data.ListOfReposResponse;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

public class ShowReposActivityViewModel extends ViewModel {
    private GitHubRepository mGitHubRepository;

    public ShowReposActivityViewModel() {
        mGitHubRepository = new GitHubRepository();
    }

    public LiveData<ListOfReposResponse> getListOfRepos(String username) {return mGitHubRepository.getListOfRepos(username);}
}
