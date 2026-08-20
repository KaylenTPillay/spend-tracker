package com.kaylentravispillay.feature.categories.di

import com.kaylentravispillay.core.domain.provider.StringProvider
import com.kaylentravispillay.feature.categories.data.provider.AndroidCategoryStringProvider
import com.kaylentravispillay.feature.categories.data.repository.CategoryRepositoryImpl
import com.kaylentravispillay.feature.categories.domain.repository.CategoryRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
internal abstract class CategoriesModule {

    @Binds
    abstract fun bindStringProvider(
        impl: AndroidCategoryStringProvider
    ): StringProvider

    @Binds
    @Singleton
    abstract fun bindCategoryRepository(
        impl: CategoryRepositoryImpl
    ): CategoryRepository
}
