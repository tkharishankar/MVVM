package com.mvvmarchitecture.di;


import android.app.Application;

import com.mvvmarchitecture.MvvmApplication;

import javax.inject.Singleton;

import dagger.BindsInstance;
import dagger.Component;
import dagger.android.AndroidInjectionModule;
import dagger.android.AndroidInjector;
import dagger.android.support.AndroidSupportInjectionModule;

@Singleton
@Component(modules = {
        AndroidSupportInjectionModule.class,
        AndroidInjectionModule.class,
        MvvmAppModule.class,
        MvvmContextModule.class,
        MvvmActivityBindingModule.class})
public interface MvvmAppComponent extends AndroidInjector<MvvmApplication> {

    @Component.Builder
    interface Builder {

        @BindsInstance
        MvvmAppComponent.Builder application(Application application);

        MvvmAppComponent build();

    }

}
