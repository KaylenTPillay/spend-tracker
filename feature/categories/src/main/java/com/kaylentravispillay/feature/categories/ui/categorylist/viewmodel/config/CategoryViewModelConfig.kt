package com.kaylentravispillay.feature.categories.ui.categorylist.viewmodel.config

import androidx.lifecycle.SavedStateHandle
import javax.inject.Inject

internal class CategoryViewModelConfig @Inject constructor(
    savedStateHandle: SavedStateHandle
) {
    val isManageMode: Boolean = savedStateHandle["isManageMode"] ?: false
}
