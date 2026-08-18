package com.kaylentravispillay.feature.categories.ui.categorylist.state

internal sealed class CategoryBodyUiState {
    data object Loading : CategoryBodyUiState()

    data object Error : CategoryBodyUiState()

    data class Content(
        val categories: List<CategoryItemUiState>
    ) : CategoryBodyUiState()
}
