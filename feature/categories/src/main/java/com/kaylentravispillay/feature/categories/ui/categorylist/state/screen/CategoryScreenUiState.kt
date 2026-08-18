package com.kaylentravispillay.feature.categories.ui.categorylist.state.screen

import com.kaylentravispillay.feature.categories.ui.categorylist.state.CategoryBodyUiState

internal data class CategoryScreenUiState(
    val isManageMode: Boolean,
    val body: CategoryBodyUiState
)
