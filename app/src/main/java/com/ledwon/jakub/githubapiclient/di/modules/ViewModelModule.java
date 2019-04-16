package com.ledwon.jakub.githubapiclient.di.modules;

import com.ledwon.jakub.githubapiclient.di.ViewModelFactory;
import com.ledwon.jakub.githubapiclient.di.ViewModelKey;
import com.ledwon.jakub.githubapiclient.ui.viewmodels.RepoDetailsActivityViewModel;
import com.ledwon.jakub.githubapiclient.ui.viewmodels.ShowReposActivityViewModel;


import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;
import dagger.Binds;
import dagger.Module;
import dagger.multibindings.IntoMap;

@Module
public abstract class ViewModelModule {

    @Binds
    abstract ViewModelProvider.Factory bindViewModelFactory(ViewModelFactory viewModelFactory);

    @Binds
    @IntoMap
    @ViewModelKey(RepoDetailsActivityViewModel.class)
    abstract ViewModel bindRepoDetailsActivityViewModel(RepoDetailsActivityViewModel viewModel);

    @Binds
    @IntoMap
    @ViewModelKey(ShowReposActivityViewModel.class)
    abstract ViewModel bindShowReposActivityViewModel(ShowReposActivityViewModel viewModel);
}
