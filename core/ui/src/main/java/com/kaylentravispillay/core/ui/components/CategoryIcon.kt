package com.kaylentravispillay.core.ui.components

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.Label
import androidx.compose.material.icons.automirrored.filled.TrendingUp
import androidx.compose.material.icons.outlined.Bolt
import androidx.compose.material.icons.outlined.DirectionsCar
import androidx.compose.material.icons.outlined.FitnessCenter
import androidx.compose.material.icons.outlined.Flight
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.LocalCafe
import androidx.compose.material.icons.outlined.MedicalServices
import androidx.compose.material.icons.outlined.Movie
import androidx.compose.material.icons.outlined.Pets
import androidx.compose.material.icons.outlined.Restaurant
import androidx.compose.material.icons.outlined.School
import androidx.compose.material.icons.outlined.ShoppingBag
import androidx.compose.material.icons.outlined.ShoppingCart
import androidx.compose.material.icons.outlined.Subscriptions
import androidx.compose.material3.Icon
import androidx.compose.material3.LocalContentColor
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import com.kaylentravispillay.core.ui.components.CategoryIconRepository.asImageVector
import com.kaylentravispillay.core.ui.state.CategoryIconUiState

@Composable
fun CategoryIcon(
    modifier: Modifier = Modifier,
    state: CategoryIconUiState,
    tint: Color? = null
) {
    Icon(
        modifier = modifier,
        imageVector = state.asImageVector(),
        contentDescription = state.key,
        tint = tint ?: LocalContentColor.current
    )
}

private object CategoryIconRepository {
    private val lookupMap = mapOf(
        CategoryIconUiState.ShoppingCart to Icons.Outlined.ShoppingCart,
        CategoryIconUiState.Restaurant to Icons.Outlined.Restaurant,
        CategoryIconUiState.LocalCafe to Icons.Outlined.LocalCafe,
        CategoryIconUiState.Home to Icons.Outlined.Home,
        CategoryIconUiState.DirectionsCar to Icons.Outlined.DirectionsCar,
        CategoryIconUiState.Flights to Icons.Outlined.Flight,
        CategoryIconUiState.MedicalServices to Icons.Outlined.MedicalServices,
        CategoryIconUiState.School to Icons.Outlined.School,
        CategoryIconUiState.Bolt to Icons.Outlined.Bolt,
        CategoryIconUiState.ShoppingBag to Icons.Outlined.ShoppingBag,
        CategoryIconUiState.Subscriptions to Icons.Outlined.Subscriptions,
        CategoryIconUiState.Movie to Icons.Outlined.Movie,
        CategoryIconUiState.FitnessCentre to Icons.Outlined.FitnessCenter,
        CategoryIconUiState.Pets to Icons.Outlined.Pets,
        CategoryIconUiState.TrendUp to Icons.AutoMirrored.Filled.TrendingUp,
        CategoryIconUiState.Label to Icons.AutoMirrored.Filled.Label
    )

    fun CategoryIconUiState.asImageVector(): ImageVector {
        return lookupMap.getOrDefault(this, Icons.AutoMirrored.Filled.Label)
    }
}
