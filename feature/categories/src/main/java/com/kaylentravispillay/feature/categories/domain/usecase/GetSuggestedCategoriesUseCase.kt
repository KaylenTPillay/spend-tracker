package com.kaylentravispillay.feature.categories.domain.usecase

import com.kaylentravispillay.core.domain.model.Category
import com.kaylentravispillay.feature.categories.domain.repository.CategoryRepository
import javax.inject.Inject

internal class GetSuggestedCategoriesUseCase @Inject constructor(
    private val repository: CategoryRepository
) {
    suspend operator fun invoke(): List<Category> {
        return repository.getSuggestedCategories()
    }
}
