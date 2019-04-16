package com.ledwon.jakub.githubapiclient.di.modules;

import com.ledwon.jakub.githubapiclient.ui.RepoDetailsActivity;
import com.ledwon.jakub.githubapiclient.ui.ShowReposActivity;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;


@Module
public abstract class ActivitiesModule {

    @ContributesAndroidInjector
    abstract ShowReposActivity contributeShowReposActivity();

    @ContributesAndroidInjector
    abstract RepoDetailsActivity contributeRepoDetailsActivity();

}
