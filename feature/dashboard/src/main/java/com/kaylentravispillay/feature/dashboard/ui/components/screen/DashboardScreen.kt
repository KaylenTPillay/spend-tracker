package com.kaylentravispillay.feature.dashboard.ui.components.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
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
import com.kaylentravispillay.core.ui.theme.TrackerTheme
import com.kaylentravispillay.feature.dashboard.ui.components.DashboardMonthlyBudgetComponent
import com.kaylentravispillay.feature.dashboard.ui.components.DashboardTotalBalanceComponent
import com.kaylentravispillay.feature.dashboard.ui.state.DashboardMonthlyBudgetUiState
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
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(contentPadding)
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(TrackerTheme.spacing.md)
        ) {
            DashboardTotalBalanceComponent(
                state = DashboardTotalBalanceUiState(
                    balance = "R14,500.50",
                    income = "R45,000.00",
                    spent = "R56,748.44"
                )
            )
            DashboardMonthlyBudgetComponent(
                state = DashboardMonthlyBudgetUiState(
                    allocation = "R29,000.00",
                    usedAmount = "R14,500.00",
                    usedProgress = 0.5F
                )
            )
        }
    }
}