package com.kaylentravispillay.feature.dashboard.ui.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.util.fastForEach
import com.kaylentravispillay.core.ui.components.ExpenseItem
import com.kaylentravispillay.core.ui.state.CategoryIconUiState
import com.kaylentravispillay.core.ui.state.ExpenseItemUiState
import com.kaylentravispillay.core.ui.theme.TrackerTheme
import com.kaylentravispillay.feature.dashboard.ui.state.RecentExpensesUiState

@Composable
internal fun RecentExpenses(
    modifier: Modifier = Modifier,
    state: RecentExpensesUiState
) {
    OutlinedCard(
        modifier = modifier,
        shape = MaterialTheme.shapes.large,
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surface
        ),
        border = BorderStroke(
            width = 1.dp,
            color = MaterialTheme.colorScheme.outlineVariant
        )
    ) {
        Column {
            Text(
                modifier = Modifier
                    .padding(TrackerTheme.spacing.lg),
                text = "Recent Expenses",
                style = MaterialTheme.typography.labelLarge,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )

            state.items.fastForEach { expenseState ->
                HorizontalDivider()
                ExpenseItem(state = expenseState)
            }
        }
    }
}

@Preview
@Composable
private fun RecentExpensesPreview_Light() {
    val previewState = RecentExpensesUiState(
        items = listOf(
            ExpenseItemUiState(
                id = "item_1",
                title = "PlayStation Monthly Subscription",
                amount = "-R299.99",
                dateStamp = "Jul 25",
                categoryName = "Gaming",
                categoryIcon = CategoryIconUiState.Subscriptions
            ),
            ExpenseItemUiState(
                id = "item_2",
                title = "Checkers Haul",
                amount = "-R1,564.50",
                dateStamp = "Today, 12:04 PM",
                categoryName = "Groceries",
                categoryIcon = CategoryIconUiState.ShoppingCart
            )
        )
    )
    TrackerTheme {
        RecentExpenses(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            state = previewState
        )
    }
}

@Preview
@Composable
private fun RecentExpensesPreview_Dark() {
    val previewState = RecentExpensesUiState(
        items = listOf(
            ExpenseItemUiState(
                id = "item_1",
                title = "PlayStation Monthly Subscription",
                amount = "-R299.99",
                dateStamp = "Jul 25",
                categoryName = "Gaming",
                categoryIcon = CategoryIconUiState.Subscriptions
            ),
            ExpenseItemUiState(
                id = "item_2",
                title = "Checkers Haul",
                amount = "-R1,564.50",
                dateStamp = "Today, 12:04 PM",
                categoryName = "Groceries",
                categoryIcon = CategoryIconUiState.ShoppingCart
            )
        )
    )
    TrackerTheme(darkTheme = true) {
        RecentExpenses(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            state = previewState
        )
    }
}
