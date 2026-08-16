package com.kaylentravispillay.core.ui.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.kaylentravispillay.core.ui.state.CategoryIconUiState
import com.kaylentravispillay.core.ui.state.ExpenseItemUiState
import com.kaylentravispillay.core.ui.theme.TrackerTheme

@Composable
fun ExpenseItem(
    modifier: Modifier = Modifier,
    state: ExpenseItemUiState,
    onItemClick: ((id: String) -> Unit)? = null
) {
    Box(
        modifier = modifier
            .apply {
                if (onItemClick == null) return@apply
                clickable { onItemClick(state.id) }
            }
    ) {
        Row(
            modifier = Modifier
                .padding(TrackerTheme.spacing.lg),
            verticalAlignment = Alignment.CenterVertically
        ) {
            CategoryIcon(
                modifier = Modifier.size(20.dp),
                state = state.categoryIcon
            )

            Spacer(modifier = Modifier.width(TrackerTheme.spacing.md))

            Column(
                modifier = Modifier.weight(1F),
                verticalArrangement = Arrangement.spacedBy(TrackerTheme.spacing.sm)
            ) {
                Text(
                    text = state.title,
                    style = MaterialTheme.typography.titleSmall,
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis
                )
                Text(
                    text = "${state.categoryName} • ${state.dateStamp}",
                    style = MaterialTheme.typography.bodySmall
                )
            }

            Spacer(modifier = Modifier.width(TrackerTheme.spacing.md))

            Text(
                text = state.amount,
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.Bold,
                softWrap = false
            )
        }
    }
}

@Preview
@Composable
private fun ExpenseItemPreview_Light() {
    val previewState = ExpenseItemUiState(
        id = "preview_id_1",
        title = "Whole Foods Market & Organic Wholesale",
        amount = "-R55,000.00",
        dateStamp = "Today, 10:34 AM",
        categoryName = "Groceries",
        categoryIcon = CategoryIconUiState.ShoppingBag
    )
    TrackerTheme {
        Surface {
            ExpenseItem(
                modifier = Modifier.fillMaxWidth(),
                state = previewState
            )
        }
    }
}

@Preview
@Composable
private fun ExpenseItemPreview_Dark() {
    val previewState = ExpenseItemUiState(
        id = "preview_id_1",
        title = "Whole Foods Market & Organic Wholesale",
        amount = "-R55,000.00",
        dateStamp = "Oct 12",
        categoryName = "Groceries",
        categoryIcon = CategoryIconUiState.FitnessCentre
    )
    TrackerTheme(darkTheme = true) {
        Surface {
            ExpenseItem(
                modifier = Modifier.fillMaxWidth(),
                state = previewState
            )
        }
    }
}
