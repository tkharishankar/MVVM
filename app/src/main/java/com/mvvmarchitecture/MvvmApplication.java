package com.mvvmarchitecture;


import com.mvvmarchitecture.di.DaggerMvvmAppComponent;

import dagger.Component;
import dagger.android.AndroidInjector;
import dagger.android.DaggerApplication;

public class MvvmApplication extends DaggerApplication {
    /**
     * Implementations should return an {@link AndroidInjector} for the concrete {@link
     * DaggerApplication}. Typically, that injector is a {@link Component}.
     */
    @Override
    protected AndroidInjector<? extends DaggerApplication> applicationInjector() {
        return DaggerMvvmAppComponent.builder().application(this).build();
    }
}
