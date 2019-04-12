package com.ledwon.jakub.githubapiclient.viewmodels;

import com.ledwon.jakub.githubapiclient.repository.GitHubRepository;
import com.ledwon.jakub.githubapiclient.repository.data.Repo;

import java.util.List;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

public class GitHubViewModel extends ViewModel {
    private GitHubRepository gitHubRepository;

    GitHubViewModel(){
        gitHubRepository = new GitHubRepository();
    }

    public LiveData<List<Repo>> getRepos(String username) {return gitHubRepository.getRepos(username);}

}
