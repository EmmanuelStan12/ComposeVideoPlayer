package com.codedev.composevideoplayer

import android.app.Application
import timber.log.Timber


class BaseApplication: Application() {

    override fun onCreate() {
        super.onCreate()
        initDI()
        initTimber()
    }

    private fun initDI(){
//        ContextProvider.setContext(this)
//        ContextProvider.setContentResolver(contentResolver)
    }

    private fun initTimber(){
        if(!BuildConfig.DEBUG)
            return

        Timber.plant(object: Timber.DebugTree(){
            override fun createStackElementTag(element: StackTraceElement): String {
                return String.format(
                    "%s:%s",
                    element.methodName,
                    super.createStackElementTag(element))
            }
        })
    }
}