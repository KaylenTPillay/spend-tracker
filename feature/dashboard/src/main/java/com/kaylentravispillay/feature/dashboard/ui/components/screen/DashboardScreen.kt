package com.kaylentravispillay.feature.dashboard.ui.components.screen

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.kaylentravispillay.core.ui.R
import com.kaylentravispillay.feature.dashboard.ui.components.DashboardTotalBalanceComponent
import com.kaylentravispillay.feature.dashboard.ui.state.DashboardTotalBalanceUiState

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DashboardScreen() {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(stringResource(R.string.app_name)) }
            )
        }
    ) { contentPadding ->
        DashboardTotalBalanceComponent(
            modifier = Modifier
                .padding(contentPadding)
                .padding(16.dp),
            state = DashboardTotalBalanceUiState(
                balance = "R14,500.50",
                income = "R45,000.00",
                spent = "R56,748.44"
            )
        )
    }
}