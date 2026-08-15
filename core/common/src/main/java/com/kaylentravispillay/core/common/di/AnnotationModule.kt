package com.kaylentravispillay.core.common.di

import com.kaylentravispillay.core.common.annotations.DefaultDispatcher
import com.kaylentravispillay.core.common.annotations.IoDispatcher
import com.kaylentravispillay.core.common.annotations.MainDispatcher
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import kotlinx.coroutines.Dispatchers
import javax.inject.Singleton

@Module
@InstallIn(Singleton::class)
object AnnotationModule {
    @Provides
    @IoDispatcher
    fun provideIoDispatcher() = Dispatchers.IO

    @Provides
    @DefaultDispatcher
    fun provideDefaultDispatcher() = Dispatchers.Default

    @Provides
    @MainDispatcher
    fun provideMainDispatcher() = Dispatchers.Main
}
