package com.kaylentravispillay.feature.categories.ui.categorylist.state

import com.kaylentravispillay.core.ui.state.CategoryIconUiState

internal data class CategoryItemUiState(
    val id: Int,
    val title: String,
    val description: String?,
    val icon: CategoryIconUiState,
    val isSelected: Boolean? = null
)
