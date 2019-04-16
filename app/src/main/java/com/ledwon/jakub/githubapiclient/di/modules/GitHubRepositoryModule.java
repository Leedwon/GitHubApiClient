package com.ledwon.jakub.githubapiclient.di.modules;

import com.ledwon.jakub.githubapiclient.data.GitHubApi;
import com.ledwon.jakub.githubapiclient.data.GitHubRepository;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module(includes = RemoteModule.class)
public class GitHubRepositoryModule {
    @Provides
    @Singleton
    GitHubRepository provideGitHubRepository(GitHubApi gitHubApi){
        return new GitHubRepository(gitHubApi);
    }
}
