package com.codedev.home._di

import com.codedev.base._di.BaseFeatureComponent
import com.codedev.home.HomeActivity
import com.codedev.home.HomeViewModel
import dagger.BindsInstance
import dagger.Component

@Component(
    modules = [HomeModule::class],
    dependencies = [BaseFeatureComponent::class]
)
@HomeScope
interface HomeFeatureComponent {

    fun inject(activity: HomeActivity)

    fun getHomeViewModel(): HomeViewModel

}