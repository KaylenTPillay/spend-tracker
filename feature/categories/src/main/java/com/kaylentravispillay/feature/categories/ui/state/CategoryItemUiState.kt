package com.kaylentravispillay.feature.categories.ui.state

import com.kaylentravispillay.core.ui.state.CategoryIconUiState

data class CategoryItemUiState(
    val id: Int,
    val title: String,
    val description: String?,
    val icon: CategoryIconUiState,
    val isSelected: Boolean? = null
)
