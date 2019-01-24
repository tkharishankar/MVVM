package com.mvvmarchitecture.di;


import com.mvvmarchitecture.detail.DetailActivity;
import com.mvvmarchitecture.list.ListActivity;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class MvvmActivityBindingModule {

    @ContributesAndroidInjector
    abstract DetailActivity detailActivity();

    @ContributesAndroidInjector
    abstract ListActivity listActivity();
}
