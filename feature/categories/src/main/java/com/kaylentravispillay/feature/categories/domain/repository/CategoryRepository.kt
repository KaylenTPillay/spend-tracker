package com.kaylentravispillay.feature.categories.domain.repository

import com.kaylentravispillay.core.domain.model.Category
import kotlinx.coroutines.flow.Flow

internal interface CategoryRepository {
    fun observeCategories(): Flow<List<Category>>

    suspend fun getSuggestedCategories(): List<Category>
}
