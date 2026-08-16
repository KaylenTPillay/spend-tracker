package com.kaylentravispillay.feature.categories.domain.usecase

import com.kaylentravispillay.core.domain.model.Category
import com.kaylentravispillay.feature.categories.domain.repository.CategoriesRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

internal class ObserveCategoriesUseCase @Inject constructor(
    private val repository: CategoriesRepository
) {
    operator fun invoke(): Flow<List<Category>> {
        return repository.observeCategories()
    }
}
