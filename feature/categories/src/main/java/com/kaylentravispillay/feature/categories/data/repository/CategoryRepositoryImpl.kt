package com.kaylentravispillay.feature.categories.data.repository

import com.kaylentravispillay.core.common.annotations.IoDispatcher
import com.kaylentravispillay.core.data.local.source.CategoryLocalSource
import com.kaylentravispillay.core.domain.model.Category
import com.kaylentravispillay.feature.categories.domain.repository.CategoryRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

internal class CategoryRepositoryImpl @Inject constructor(
    private val categoryLocalSource: CategoryLocalSource,
    @IoDispatcher private val dispatcher: CoroutineDispatcher
) : CategoryRepository {
    override fun observeCategories(): Flow<List<Category>> {
        return categoryLocalSource.observeCategories()
            .flowOn(dispatcher)
    }
}
