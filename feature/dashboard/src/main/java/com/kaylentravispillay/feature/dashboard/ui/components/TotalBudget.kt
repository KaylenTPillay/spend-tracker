package com.kaylentravispillay.feature.dashboard.ui.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDownward
import androidx.compose.material.icons.filled.ArrowUpward
import androidx.compose.material.icons.outlined.AccountBalance
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.kaylentravispillay.core.ui.theme.TrackerTheme
import com.kaylentravispillay.feature.dashboard.R
import com.kaylentravispillay.feature.dashboard.ui.state.TotalBalanceUiState

@Composable
internal fun TotalBalance(
    modifier: Modifier = Modifier,
    state: TotalBalanceUiState
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
        Box(
            modifier = Modifier
                .padding(TrackerTheme.spacing.lg)
        ) {
            Column {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(
                        text = stringResource(R.string.total_balance),
                        style = MaterialTheme.typography.labelLarge,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                    Icon(
                        modifier = Modifier.size(20.dp),
                        imageVector = Icons.Outlined.AccountBalance,
                        contentDescription = null,
                        tint = MaterialTheme.colorScheme.primary
                    )
                }

                Spacer(modifier = Modifier.height(TrackerTheme.spacing.sm))

                Text(
                    modifier = Modifier.fillMaxWidth(),
                    text = state.balance,
                    style = TrackerTheme.Typography.numericLarge,
                    color = MaterialTheme.colorScheme.onBackground
                )

                Spacer(modifier = Modifier.height(TrackerTheme.spacing.lg))

                HorizontalDivider()

                Spacer(modifier = Modifier.height(TrackerTheme.spacing.lg))

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    DashboardTotalBalanceBreakdownComponent(
                        title = stringResource(R.string.income_this_month),
                        icon = Icons.Filled.ArrowUpward,
                        total = state.income,
                        semanticColor = TrackerTheme.semanticColor.income
                    )

                    DashboardTotalBalanceBreakdownComponent(
                        title = stringResource(R.string.spent_this_month),
                        icon = Icons.Filled.ArrowDownward,
                        total = state.spent,
                        semanticColor = TrackerTheme.semanticColor.expense
                    )
                }
            }
        }
    }
}

@Composable
private fun DashboardTotalBalanceBreakdownComponent(
    modifier: Modifier = Modifier,
    title: String,
    icon: ImageVector,
    semanticColor: Color,
    total: String
) {
    Column(modifier = modifier) {
        Text(
            text = title,
            style = MaterialTheme.typography.labelSmall,
            color = MaterialTheme.colorScheme.onSurfaceVariant
        )

        Row(
            horizontalArrangement = Arrangement.Start,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                modifier = Modifier.size(20.dp),
                imageVector = icon,
                contentDescription = null,
                tint = semanticColor
            )
            Text(
                text = total,
                style = MaterialTheme.typography.labelLarge,
                color = semanticColor
            )
        }
    }
}

@Preview
@Composable
private fun TotalBalance_Light_Preview() {
    val previewState = TotalBalanceUiState(
        balance = "R12,450.00",
        income = "R71,200.21",
        spent = "R38,200.76"
    )

    TrackerTheme {
        TotalBalance(
            modifier = Modifier.fillMaxWidth(),
            state = previewState
        )
    }
}

@Preview
@Composable
private fun TotalBalance_Dark_Preview() {
    val previewState = TotalBalanceUiState(
        balance = "R12,450.00",
        income = "R71,200.21",
        spent = "R38,200.76"
    )

    TrackerTheme(darkTheme = true) {
        TotalBalance(
            modifier = Modifier.fillMaxWidth(),
            state = previewState
        )
    }
}
