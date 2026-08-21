package com.kaylentravispillay.feature.categories.ui.categorylist.component.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.outlined.ArrowBack
import androidx.compose.material.icons.automirrored.outlined.ArrowForward
import androidx.compose.material.icons.outlined.Add
import androidx.compose.material.icons.outlined.CheckCircle
import androidx.compose.material.icons.outlined.Circle
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FilledIconButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconToggleButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.key
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.kaylentravispillay.core.ui.state.CategoryIconUiState
import com.kaylentravispillay.core.ui.theme.TrackerTheme
import com.kaylentravispillay.feature.categories.R
import com.kaylentravispillay.feature.categories.ui.categorylist.component.CategoryItem
import com.kaylentravispillay.feature.categories.ui.categorylist.state.CategoryBodyUiState
import com.kaylentravispillay.feature.categories.ui.categorylist.state.CategoryItemUiState
import com.kaylentravispillay.feature.categories.ui.categorylist.state.screen.CategoryScreenUiState
import com.kaylentravispillay.feature.categories.ui.categorylist.viewmodel.CategoryViewModel

@Composable
internal fun CategoryScreenRoot(
    modifier: Modifier = Modifier,
    viewModel: CategoryViewModel = hiltViewModel(),
    onNavigationIconClick: () -> Unit
) {
    val state by viewModel.state.collectAsStateWithLifecycle()

    CategoriesScreen(
        modifier = modifier,
        state = state,
        onNavigationIconClick = onNavigationIconClick,
        onConfirmCategoriesClick = viewModel::onConfirmCategoriesClick,
        onCategoryClick = viewModel::onCategoryClick,
        onCreateCategoryClick = viewModel::onCreateCategoryClick
    )
}

@Composable
internal fun CategoriesScreen(
    modifier: Modifier = Modifier,
    state: CategoryScreenUiState,
    onNavigationIconClick: () -> Unit,
    onCreateCategoryClick: () -> Unit,
    onCategoryClick: (id: Int) -> Unit,
    onConfirmCategoriesClick: () -> Unit
) {

    Scaffold(
        modifier = modifier,
        topBar = {
            CategoryTopAppBar(
                isManageMode = state.isManageMode,
                onNavigationIconClick = onNavigationIconClick,
                onCreateCategoryClick = onCreateCategoryClick
            )
        },
        bottomBar = {
            CategoryBottomBar(
                isManageMode = state.isManageMode,
                hasSelectedCategories = state.isConfirmEnabled,
                onConfirmCategoriesClick = onConfirmCategoriesClick
            )
        }
    ) { contentPadding ->
        Box(
            modifier = Modifier
                .padding(contentPadding)
                .fillMaxSize()
        ) {
            when (state.body) {
                is CategoryBodyUiState.Error -> {
                    CategoryError()
                }

                is CategoryBodyUiState.Loading -> {
                    CategoryLoading()
                }

                is CategoryBodyUiState.Content -> {
                    CategoryContent(
                        categories = state.body.categories,
                        isManageMode = state.isManageMode,
                        onCategoryClick = onCategoryClick
                    )
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun CategoryTopAppBar(
    modifier: Modifier = Modifier,
    isManageMode: Boolean,
    onNavigationIconClick: () -> Unit,
    onCreateCategoryClick: () -> Unit
) {
    val actionsComposable: @Composable RowScope.() -> Unit = if (isManageMode) {
        {
            IconButton(onClick = onCreateCategoryClick) {
                Icon(
                    imageVector = Icons.Outlined.Add,
                    contentDescription = "Create Category"
                )
            }
        }
    } else {
        {}
    }

    TopAppBar(
        modifier = modifier,
        title = { Text(text = stringResource(R.string.categories)) },
        navigationIcon = {
            IconButton(onClick = onNavigationIconClick) {
                Icon(
                    imageVector = Icons.AutoMirrored.Outlined.ArrowBack,
                    contentDescription = "Back"
                )
            }
        },
        actions = actionsComposable
    )
}

@Composable
private fun CategoryBottomBar(
    modifier: Modifier = Modifier,
    isManageMode: Boolean,
    hasSelectedCategories: Boolean,
    onConfirmCategoriesClick: () -> Unit
) {
    if (isManageMode) return

    BottomAppBar(modifier = modifier) {
        FilledIconButton(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = TrackerTheme.spacing.md)
                .padding(bottom = 2.dp),
            onClick = onConfirmCategoriesClick,
            enabled = hasSelectedCategories
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(TrackerTheme.spacing.sm)
            ) {
                Text(
                    text = stringResource(R.string.confirm_categories),
                    style = MaterialTheme.typography.titleMedium,
                )
                Icon(
                    imageVector = Icons.AutoMirrored.Outlined.ArrowForward,
                    contentDescription = "Continue"
                )
            }
        }
    }
}

@Composable
private fun CategoryError(
    modifier: Modifier = Modifier
) {
    Text(modifier = modifier, text = "Error")
}

@Composable
private fun CategoryLoading(
    modifier: Modifier = Modifier
) {
    Text(modifier = modifier, text = "Loading")
}

@Composable
private fun CategoryContent(
    modifier: Modifier = Modifier,
    categories: List<CategoryItemUiState>,
    isManageMode: Boolean,
    onCategoryClick: (id: Int) -> Unit
) {
    LazyColumn(
        modifier = modifier,
        contentPadding = PaddingValues(TrackerTheme.spacing.md),
        verticalArrangement = Arrangement.spacedBy(TrackerTheme.spacing.sm)
    ) {
        items(categories) { category ->
            val categoryItemEndIcon: (@Composable () -> Unit)? = if (!isManageMode) {
                {
                    CategoryItemEndIcon(
                        isSelected = category.isSelected,
                        onCategoryEndIconClick = { onCategoryClick(category.id) }
                    )
                }
            } else {
                null
            }

            key(category.id) {
                CategoryItem(
                    state = category,
                    endIcon = categoryItemEndIcon,
                    onCategoryClick = onCategoryClick
                )
            }
        }
    }
}

@Composable
private fun CategoryItemEndIcon(
    modifier: Modifier = Modifier,
    isSelected: Boolean?,
    onCategoryEndIconClick: () -> Unit
) {
    val isChecked = isSelected ?: false

    IconToggleButton(
        modifier = modifier,
        checked = isChecked,
        onCheckedChange = { onCategoryEndIconClick() }
    ) {
        if (isChecked) {
            Icon(
                imageVector = Icons.Outlined.CheckCircle,
                contentDescription = null
            )
        } else {
            Icon(
                imageVector = Icons.Outlined.Circle,
                contentDescription = null
            )
        }
    }
}

@Preview
@Composable
internal fun CategoryScreenPreview_Dark() {
    val previewState = CategoryScreenUiState(
        isManageMode = false,
        body = CategoryBodyUiState.Content(
            categories = listOf(
                CategoryItemUiState(
                    id = 1,
                    title = "Test-Title-1",
                    description = "Test-Description-1",
                    icon = CategoryIconUiState.Restaurant
                ),
                CategoryItemUiState(
                    id = 2,
                    title = "Test-Title-2",
                    description = "Test-Description-2",
                    icon = CategoryIconUiState.Pets,
                    isSelected = true
                )
            )
        )
    )
    TrackerTheme(darkTheme = true) {
        CategoriesScreen(
            state = previewState,
            onNavigationIconClick = {},
            onCreateCategoryClick = {},
            onCategoryClick = {},
            onConfirmCategoriesClick = {}
        )
    }
}

@Preview
@Composable
internal fun CategoryScreenPreview_Light() {
    val previewState = CategoryScreenUiState(
        isManageMode = true,
        body = CategoryBodyUiState.Content(
            categories = listOf(
                CategoryItemUiState(
                    id = 1,
                    title = "Test-Title-1",
                    description = "Test-Description-1",
                    icon = CategoryIconUiState.Restaurant
                ),
                CategoryItemUiState(
                    id = 2,
                    title = "Test-Title-2",
                    description = "Test-Description-2",
                    icon = CategoryIconUiState.Pets,
                    isSelected = true
                )
            )
        )
    )
    TrackerTheme(darkTheme = false) {
        CategoriesScreen(
            state = previewState,
            onNavigationIconClick = {},
            onCreateCategoryClick = {},
            onCategoryClick = {},
            onConfirmCategoriesClick = {}
        )
    }
}
