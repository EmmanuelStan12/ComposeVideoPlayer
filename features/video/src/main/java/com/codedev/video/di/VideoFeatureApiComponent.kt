package com.codedev.video.di

import com.codedev.video.VideoFeatureApi
import dagger.Component

@Component(
    modules = [VideoFeatureModule::class],
)
@VideoFeatureScope
interface VideoFeatureApiComponent {


    companion object {
        private var instance: VideoFeatureApiComponent? = null

        @JvmStatic
        fun getInstance(): VideoFeatureApiComponent {
            instance = DaggerVideoFeatureApiComponent
                .builder()
                .build()
            return instance as VideoFeatureApiComponent
        }
    }

    fun getVideoFeatureApi(): VideoFeatureApi
}