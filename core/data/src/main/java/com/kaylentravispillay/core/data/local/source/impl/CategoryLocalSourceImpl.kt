package com.kaylentravispillay.core.data.local.source.impl

import com.kaylentravispillay.core.common.annotations.IoDispatcher
import com.kaylentravispillay.core.data.local.database.daos.CategoryDao
import com.kaylentravispillay.core.data.local.source.CategoryLocalSource
import com.kaylentravispillay.core.data.local.source.model.mapper.MapperLocalSource.toEntity
import com.kaylentravispillay.core.data.local.source.model.mapper.MapperLocalSource.toLocal
import com.kaylentravispillay.core.domain.model.Category
import jakarta.inject.Inject
import kotlinx.coroutines.CancellationException
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext

internal class CategoryLocalSourceImpl @Inject constructor(
    private val categoryDao: CategoryDao,
    @IoDispatcher private val dispatcher: CoroutineDispatcher
) : CategoryLocalSource {
    override suspend fun addCategory(category: Category) {
        withContext(dispatcher) {
            categoryDao.addCategory(category.toEntity())
        }
    }

    override fun observeCategories(): Flow<List<Category>> {
        return categoryDao.observeCategories()
            .map { entityCollection -> entityCollection.map { entity -> entity.toLocal() } }
            .flowOn(dispatcher)
    }
}
