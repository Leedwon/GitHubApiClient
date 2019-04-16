package com.ledwon.jakub.githubapiclient.di.modules;

import android.app.Application;
import android.content.Context;

import dagger.Module;
import dagger.Provides;

@Module
public class AppModule {
    @Provides
    Context provideContext(Application context){
        return context;
    }
}
