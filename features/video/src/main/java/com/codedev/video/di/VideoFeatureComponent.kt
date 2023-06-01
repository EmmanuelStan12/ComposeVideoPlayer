package com.codedev.video.di

import com.codedev.video.VideoFeatureApi
import dagger.Component

@Component
interface VideoFeatureComponent {

    @Component.Builder
    companion object {
        private var instance: VideoFeatureComponent? = null

        fun getInstance(): VideoFeatureComponent {
            if (instance == null) {

            }
            return instance as VideoFeatureComponent
        }
    }

    fun getVideoFeatureApi(): VideoFeatureApi
}