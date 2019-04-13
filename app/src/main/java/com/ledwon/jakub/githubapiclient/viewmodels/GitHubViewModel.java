package com.ledwon.jakub.githubapiclient.viewmodels;

import com.ledwon.jakub.githubapiclient.repository.GitHubRepository;
import com.ledwon.jakub.githubapiclient.repository.data.Repo;

import java.util.List;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

public class GitHubViewModel extends ViewModel {
    private GitHubRepository gitHubRepository;

    public GitHubViewModel() {
        gitHubRepository = new GitHubRepository();
    }

    public LiveData<List<Repo>> getListRepos(String username) {return gitHubRepository.getListRepos(username);}

    public LiveData<Repo> getRepo(String username, String repo) {return gitHubRepository.getRepo(username, repo);}
}
