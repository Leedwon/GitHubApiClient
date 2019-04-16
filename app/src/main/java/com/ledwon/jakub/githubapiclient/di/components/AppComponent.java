package com.ledwon.jakub.githubapiclient.di.components;

import android.app.Application;

import com.ledwon.jakub.githubapiclient.App;
import com.ledwon.jakub.githubapiclient.di.modules.ActivitiesModule;
import com.ledwon.jakub.githubapiclient.di.modules.GitHubRepositoryModule;
import com.ledwon.jakub.githubapiclient.di.modules.ViewModelModule;

import javax.inject.Singleton;

import dagger.BindsInstance;
import dagger.Component;
import dagger.android.AndroidInjector;
import dagger.android.support.AndroidSupportInjectionModule;

@Singleton
@Component(modules = {ActivitiesModule.class, ViewModelModule.class, GitHubRepositoryModule.class, AndroidSupportInjectionModule.class})
public interface AppComponent extends AndroidInjector<App> {

    @Component.Builder
    interface Builder {
        @BindsInstance
        AppComponent.Builder application(Application application);

        AppComponent build();
    }

    void inject(App app);
}
