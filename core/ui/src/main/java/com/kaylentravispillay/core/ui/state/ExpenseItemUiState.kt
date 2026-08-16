package com.kaylentravispillay.core.ui.state

data class ExpenseItemUiState(
    val id: String,
    val title: String,
    val amount: String,
    val dateStamp: String,
    val categoryName: String,
    val categoryIcon: CategoryIconUiState
)
