package com.codedev.video.di

import com.codedev.video.VideoFeatureApi
import com.codedev.video.VideoFeatureImpl
import dagger.Module
import dagger.Provides

@Module
object VideoFeatureModule {

    @Provides
    @VideoFeatureScope
    fun provideVideoFeatureApi(): VideoFeatureApi {
        return VideoFeatureImpl()
    }

}