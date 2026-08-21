package com.kaylentravispillay.feature.categories.ui.categorylist.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kaylentravispillay.core.common.annotations.DefaultDispatcher
import com.kaylentravispillay.core.domain.model.Category
import com.kaylentravispillay.feature.categories.domain.usecase.GetSuggestedCategoriesUseCase
import com.kaylentravispillay.feature.categories.domain.usecase.ObserveStoredCategoriesUseCase
import com.kaylentravispillay.feature.categories.ui.categorylist.state.CategoryBodyUiState
import com.kaylentravispillay.feature.categories.ui.categorylist.state.screen.CategoryScreenUiState
import com.kaylentravispillay.feature.categories.ui.categorylist.state.mapper.MapperCategoryUiState.toUiState
import com.kaylentravispillay.feature.categories.ui.categorylist.viewmodel.config.CategoryViewModelConfig
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
internal class CategoryViewModel @Inject constructor(
    observeStoredCategories: ObserveStoredCategoriesUseCase,
    getSuggestedCategories: GetSuggestedCategoriesUseCase,
    private val config: CategoryViewModelConfig,
    @DefaultDispatcher private val dispatcher: CoroutineDispatcher
) : ViewModel() {
    private val _selectedCategoryIds = MutableStateFlow<Set<Int>>(emptySet())

    private val suggestedCategoriesFlow = flow {
        emit(getSuggestedCategories())
    }

    val state: StateFlow<CategoryScreenUiState> = combine(
        observeStoredCategories(),
        suggestedCategoriesFlow,
        _selectedCategoryIds,
        ::processData
    ).flowOn(
        dispatcher
    ).stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(stopTimeoutMillis = 5_000L),
        initialValue = CategoryScreenUiState(
            isManageMode = config.isManageMode,
            body = CategoryBodyUiState.Loading
        )
    )

    fun onConfirmCategoriesClick() {
        if (config.isManageMode) return

        // TODO(1): The confirm category button should complete the feature.
        // 1. We need to save all of the selected items.
        // 2. We need to notify that the feature has been complete.
    }

    fun onCategoryClick(id: Int) {
        if (config.isManageMode) {
            // TODO(2): go to the edit category screen.
        } else {
            _selectedCategoryIds.update { selectedIds ->
                if (selectedIds.contains(id)) selectedIds - id else selectedIds + id
            }
        }
    }

    fun onCreateCategoryClick() {
        if (!config.isManageMode) return
    }

    private fun processData(
        storedCategories: List<Category>,
        suggestedCategories: List<Category>,
        selectedIds: Set<Int>
    ): CategoryScreenUiState {
        val categoryCollection = if (config.isManageMode) storedCategories else suggestedCategories
        val categoriesUiStateCollection = categoryCollection.map {
            it.toUiState().copy(isSelected = selectedIds.contains(it.id))
        }

        val hasSelectedCategories = selectedIds.isNotEmpty()

        return CategoryScreenUiState(
            isManageMode = config.isManageMode,
            body = CategoryBodyUiState.Content(categories = categoriesUiStateCollection),
            isConfirmEnabled = hasSelectedCategories
        )
    }
}
