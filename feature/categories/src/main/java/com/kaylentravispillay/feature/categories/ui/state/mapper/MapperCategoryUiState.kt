package com.kaylentravispillay.feature.categories.ui.state.mapper

import com.kaylentravispillay.core.domain.model.Category
import com.kaylentravispillay.core.ui.state.CategoryIconUiState
import com.kaylentravispillay.feature.categories.ui.state.CategoryItemUiState

object MapperCategoryUiState {
    fun Category.toUiState(): CategoryItemUiState {
        return CategoryItemUiState(
            id = id,
            title = name,
            description = description,
            icon = CategoryIconUiState.fromValue(iconType)
        )
    }
}
