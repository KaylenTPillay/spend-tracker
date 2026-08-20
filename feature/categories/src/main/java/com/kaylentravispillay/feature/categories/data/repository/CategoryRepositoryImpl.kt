package com.kaylentravispillay.feature.categories.data.repository

import com.kaylentravispillay.core.common.annotations.DefaultDispatcher
import com.kaylentravispillay.core.common.annotations.IoDispatcher
import com.kaylentravispillay.core.data.local.source.CategoryLocalSource
import com.kaylentravispillay.core.domain.model.Category
import com.kaylentravispillay.core.domain.provider.StringProvider
import com.kaylentravispillay.feature.categories.domain.model.DefaultCategories
import com.kaylentravispillay.feature.categories.domain.repository.CategoryRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.withContext
import javax.inject.Inject

internal class CategoryRepositoryImpl @Inject constructor(
    private val categoryLocalSource: CategoryLocalSource,
    private val provider: StringProvider,
    @IoDispatcher private val ioDispatcher: CoroutineDispatcher,
    @DefaultDispatcher private val defaultDispatcher: CoroutineDispatcher
) : CategoryRepository {
    override fun observeCategories(): Flow<List<Category>> {
        return categoryLocalSource.observeCategories()
            .flowOn(ioDispatcher)
    }

    override suspend fun getSuggestedCategories(): List<Category> {
        return withContext(defaultDispatcher) {
            DefaultCategories.entries.map {
                Category(
                    id = 0,
                    name = provider.getString(it.titleKey),
                    description = provider.getString(it.descriptionKey),
                    iconType = it.icon
                )
            }
        }
    }
}
