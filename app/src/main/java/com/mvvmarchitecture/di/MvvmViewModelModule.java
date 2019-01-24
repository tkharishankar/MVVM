package com.mvvmarchitecture.di;

import android.arch.lifecycle.ViewModel;

import com.mvvmarchitecture.detail.DetailViewModel;
import com.mvvmarchitecture.list.ListViewModel;

import dagger.Binds;
import dagger.Module;
import dagger.multibindings.IntoMap;

@Module
public abstract class MvvmViewModelModule {


    @Binds
    @IntoMap
    @ViewModelKey(DetailViewModel.class)
    abstract ViewModel bindDetailViewModel(DetailViewModel detailViewModel);

    @Binds
    @IntoMap
    @ViewModelKey(ListViewModel.class)
    abstract ViewModel bindListViewModel(ListViewModel listViewModel);
}
