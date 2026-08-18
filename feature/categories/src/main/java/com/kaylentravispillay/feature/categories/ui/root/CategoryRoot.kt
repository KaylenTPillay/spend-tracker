package com.kaylentravispillay.feature.categories.ui.root

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.kaylentravispillay.feature.categories.ui.categorylist.component.screen.CategoryScreenRoot

@Composable
fun CategoryRoot(
    modifier: Modifier = Modifier,
    onNavigationIconClick: () -> Unit
) {
    CategoryScreenRoot(
        modifier = modifier,
        onNavigationIconClick = onNavigationIconClick
    )
}
