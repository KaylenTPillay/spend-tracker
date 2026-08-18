package com.kaylentravispillay.feature.categories.ui.state.screen

import com.kaylentravispillay.feature.categories.ui.state.CategoryBodyUiState

data class CategoryScreenUiState(
    val isManageMode: Boolean,
    val body: CategoryBodyUiState
)
