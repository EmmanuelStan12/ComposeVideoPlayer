package com.codedev.video

import com.codedev.feature_api.FeatureApi

interface VideoFeatureApi: FeatureApi {

    fun videoRoute(): String
}