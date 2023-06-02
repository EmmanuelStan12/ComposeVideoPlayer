package com.codedev.data_lib._di

import com.codedev.data_lib.repositories.VideoRepositoryImpl
import com.codedev.data_lib.repositories.interfaces.IVideoRepository
import com.codedev.room_lib.dao.QueryDao
import com.codedev.room_lib.dao.VideoDao
import dagger.Module
import dagger.Provides

@Module
object DataModule {

    @Provides
    fun provideVideoRepository(
        queryDao: QueryDao,
        videoDao: VideoDao,
    ): IVideoRepository {
        return VideoRepositoryImpl(videoDao, queryDao)
    }
}