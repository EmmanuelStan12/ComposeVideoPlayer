package com.codedev.base._di

import android.app.Application
import com.codedev.video.VideoFeatureApi
import com.freexitnow.context_provider_lib.ContextProvider
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

@Component(
    modules = [BaseFeatureModule::class]
)
@Singleton
interface BaseFeatureComponent {

    companion object {
        private var instance: BaseFeatureComponent? = null

        fun getInstance(): BaseFeatureComponent {
            if (instance == null) {
                instance = DaggerBaseFeatureComponent.builder()
                    .application(ContextProvider.getApplication())
                    .build()
            }

            return instance as BaseFeatureComponent
        }
    }

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(application: Application): Builder
        fun build(): BaseFeatureComponent
    }

    fun getVideoFeatureApi(): com.codedev.video.VideoFeatureApi
}