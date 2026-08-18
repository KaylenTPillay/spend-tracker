package com.kaylentravispillay.feature.categories.ui.categorylist.component

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Preview
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.kaylentravispillay.core.ui.components.CategoryIcon
import com.kaylentravispillay.core.ui.state.CategoryIconUiState
import com.kaylentravispillay.core.ui.theme.TrackerTheme
import com.kaylentravispillay.feature.categories.ui.categorylist.state.CategoryItemUiState

@Composable
internal fun CategoryItem(
    modifier: Modifier = Modifier,
    state: CategoryItemUiState,
    endIcon: (@Composable () -> Unit)? = null,
    onCategoryClick: (id: Int) -> Unit
) {
    val hasEndIcon = endIcon != null

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
        Box(
            modifier = Modifier
                .clickable { onCategoryClick(state.id) }
                .padding(vertical = TrackerTheme.spacing.md)
                .padding(start = TrackerTheme.spacing.md)
                .padding(end = if (hasEndIcon) TrackerTheme.spacing.md else TrackerTheme.spacing.sm)
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(TrackerTheme.spacing.md)
            ) {
                CategoryIcon(state = state.icon)

                CategoryItemContent(
                    title = state.title,
                    description = state.description
                )

                endIcon?.invoke()
            }
        }
    }
}

@Composable
private fun RowScope.CategoryItemContent(
    modifier: Modifier = Modifier,
    title: String,
    description: String?
) {
    Column(
        modifier = modifier.weight(1f)
    ) {
        Text(
            text = title,
            style = MaterialTheme.typography.titleMedium
        )

        description?.let { description ->
            Text(
                text = description,
                style = MaterialTheme.typography.bodySmall
            )
        }
    }
}

@Preview
@Composable
private fun CategoryItemEditablePreview_Light() {
    val previewState = CategoryItemUiState(
        id = 1,
        title = "Food & Drink",
        description = "Groceries, restaurants, delivery",
        icon = CategoryIconUiState.Restaurant,
        isSelected = null
    )

    TrackerTheme(darkTheme = false) {
        CategoryItem(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            state = previewState,
            onCategoryClick = {}
        )
    }
}

@Preview
@Composable
private fun CategoryItemEditablePreview_Dark() {
    val previewState = CategoryItemUiState(
        id = 1,
        title = "Food & Drink",
        description = "Groceries, restaurants, delivery",
        icon = CategoryIconUiState.Restaurant,
        isSelected = null
    )

    TrackerTheme(darkTheme = true) {
        CategoryItem(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            state = previewState,
            onCategoryClick = {}
        )
    }
}

@Preview
@Composable
private fun CategoryItemWithEndIconPreview_Light() {
    val previewState = CategoryItemUiState(
        id = 1,
        title = "Food & Drink",
        description = "Groceries, restaurants, delivery",
        icon = CategoryIconUiState.Restaurant,
        isSelected = null
    )

    TrackerTheme(darkTheme = false) {
        CategoryItem(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            state = previewState,
            onCategoryClick = {},
            endIcon = {
                Icon(
                    imageVector = Icons.Outlined.Preview,
                    contentDescription = null
                )
            }
        )
    }
}

@Preview
@Composable
private fun CategoryItemWithEndIconPreview_Dark() {
    val previewState = CategoryItemUiState(
        id = 1,
        title = "Food & Drink",
        description = "Groceries, restaurants, delivery",
        icon = CategoryIconUiState.Restaurant,
        isSelected = true
    )

    TrackerTheme(darkTheme = true) {
        CategoryItem(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            state = previewState,
            onCategoryClick = {},
            endIcon = {
                Icon(
                    imageVector = Icons.Outlined.Preview,
                    contentDescription = null
                )
            }
        )
    }
}
