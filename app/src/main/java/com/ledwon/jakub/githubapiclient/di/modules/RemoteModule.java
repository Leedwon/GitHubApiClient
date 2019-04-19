package com.ledwon.jakub.githubapiclient.di.modules;

import com.ledwon.jakub.githubapiclient.data.GitHubApi;

import javax.inject.Inject;
import javax.inject.Named;
import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

@Module
public class RemoteModule {
    private static final String BASE_URL = "https://api.github.com/";

    @Provides
    @Singleton
    Retrofit provideRetrofit() {
        return new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    @Provides
    @Singleton
    GitHubApi provideGithubApi(Retrofit retrofit){
        return retrofit.create(GitHubApi.class);
    }
}
