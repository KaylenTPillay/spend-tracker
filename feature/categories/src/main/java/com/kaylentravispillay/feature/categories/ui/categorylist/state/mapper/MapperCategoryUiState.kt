package com.kaylentravispillay.feature.categories.ui.categorylist.state.mapper

import com.kaylentravispillay.core.domain.model.Category
import com.kaylentravispillay.core.domain.model.CategoryIconType
import com.kaylentravispillay.core.ui.state.CategoryIconUiState
import com.kaylentravispillay.feature.categories.ui.categorylist.state.CategoryItemUiState

internal object MapperCategoryUiState {
    fun Category.toUiState(): CategoryItemUiState {
        return CategoryItemUiState(
            id = id,
            title = name,
            description = description,
            icon = iconType.toUiState()
        )
    }

    private fun CategoryIconType.toUiState(): CategoryIconUiState {
        return CategoryIconUiState.entries.find {
            it.name == name
        } ?: CategoryIconUiState.Label
    }
}
