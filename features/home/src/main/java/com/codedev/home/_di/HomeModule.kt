package com.codedev.home._di

import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.codedev.base._di.ViewModelKey
import com.codedev.home.HomeViewModel
import dagger.Module
import dagger.Provides
import dagger.multibindings.IntoMap
import javax.inject.Provider

@Module
object HomeModule {

    @Provides
    @HomeScope
    fun provideHomeViewModelFactory(
        map: Map<Class<out ViewModel>,
                @JvmSuppressWildcards Provider<ViewModel>>
    ): MHomeViewModelFactory = MHomeViewModelFactory(map)

    @Provides
    @IntoMap
    @ViewModelKey(HomeViewModel::class)
    @HomeScope
    fun provideHomeViewModel(
        viewModelFactory: MHomeViewModelFactory,
        activity: AppCompatActivity
    ): HomeViewModel =
        ViewModelProvider(activity.viewModelStore,viewModelFactory)[HomeViewModel::class.java]


}