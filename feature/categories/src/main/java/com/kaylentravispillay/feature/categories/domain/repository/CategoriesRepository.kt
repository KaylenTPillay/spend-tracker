package com.kaylentravispillay.feature.categories.domain.repository

import com.kaylentravispillay.core.domain.model.Category
import kotlinx.coroutines.flow.Flow

internal interface CategoriesRepository {
    fun observeCategories(): Flow<List<Category>>
}
