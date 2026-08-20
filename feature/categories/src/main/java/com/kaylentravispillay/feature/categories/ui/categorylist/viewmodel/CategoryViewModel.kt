package com.kaylentravispillay.feature.categories.ui.categorylist.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kaylentravispillay.core.common.annotations.DefaultDispatcher
import com.kaylentravispillay.core.domain.model.Category
import com.kaylentravispillay.feature.categories.domain.usecase.GetSuggestedCategoriesUseCase
import com.kaylentravispillay.feature.categories.domain.usecase.ObserveCategoriesUseCase
import com.kaylentravispillay.feature.categories.ui.categorylist.state.CategoryBodyUiState
import com.kaylentravispillay.feature.categories.ui.categorylist.state.screen.CategoryScreenUiState
import com.kaylentravispillay.feature.categories.ui.categorylist.state.mapper.MapperCategoryUiState.toUiState
import com.kaylentravispillay.feature.categories.ui.categorylist.viewmodel.config.CategoryViewModelConfig
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

@HiltViewModel
internal class CategoryViewModel @Inject constructor(
    observeCategories: ObserveCategoriesUseCase,
    getSuggestedCategories: GetSuggestedCategoriesUseCase,
    private val config: CategoryViewModelConfig,
    @DefaultDispatcher private val dispatcher: CoroutineDispatcher
) : ViewModel() {
    private val suggestedCategoriesFlow = flow {
        emit(getSuggestedCategories())
    }

    val state: StateFlow<CategoryScreenUiState> = combine(
        observeCategories(),
        suggestedCategoriesFlow
    ) { (categories, suggestedCategories) ->
        processData(categories, suggestedCategories)
    }.flowOn(
        dispatcher
    ).stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(stopTimeoutMillis = 5_000L),
        initialValue = CategoryScreenUiState(
            isManageMode = config.isManageMode,
            body = CategoryBodyUiState.Loading
        )
    )

    private fun processData(
        categories: List<Category>,
        suggestedCategories: List<Category>
    ): CategoryScreenUiState {
        val categoriesUiStateCollection = categories.map { category ->
            category.toUiState()
        }.ifEmpty {
            suggestedCategories.map { it.toUiState() }
        }

        return CategoryScreenUiState(
            isManageMode = config.isManageMode,
            body = CategoryBodyUiState.Content(categories = categoriesUiStateCollection)
        )
    }
}
