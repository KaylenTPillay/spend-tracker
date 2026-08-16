package com.kaylentravispillay.core.data.local.source.impl

import com.kaylentravispillay.core.common.annotations.IoDispatcher
import com.kaylentravispillay.core.data.local.database.daos.CategoryDao
import com.kaylentravispillay.core.data.local.source.CategoryLocalSource
import com.kaylentravispillay.core.data.local.source.model.mapper.MapperLocalSource
import com.kaylentravispillay.core.data.local.source.model.mapper.MapperLocalSource.toEntity
import com.kaylentravispillay.core.data.local.source.model.mapper.MapperLocalSource.toLocal
import com.kaylentravispillay.core.domain.model.Category
import jakarta.inject.Inject
import kotlinx.coroutines.CancellationException
import kotlinx.coroutines.CoroutineDispatcher
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

    @Suppress("TooGenericExceptionCaught")
    override suspend fun getCategories(): Result<List<Category>> {
        return withContext(dispatcher) {
            try {
                val categories = categoryDao.getCategories().map { entity ->
                    entity.toLocal()
                }
                Result.success(categories)
            } catch (exp: CancellationException) {
                throw exp
            } catch (exp: Exception) {
                Result.failure(exp)
            }
        }
    }
}
