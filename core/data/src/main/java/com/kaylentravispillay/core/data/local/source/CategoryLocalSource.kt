package com.kaylentravispillay.core.data.local.source

import com.kaylentravispillay.core.domain.model.Category
import kotlinx.coroutines.flow.Flow

interface CategoryLocalSource {
    suspend fun addCategory(category: Category)

    fun observeCategories(): Flow<List<Category>>
}
