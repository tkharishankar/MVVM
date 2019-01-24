package com.mvvmarchitecture.di;

import android.app.Application;
import android.content.Context;

import dagger.Binds;
import dagger.Module;

@Module
public abstract class MvvmContextModule {

    @Binds
    abstract Context provideContext(Application application);
}
