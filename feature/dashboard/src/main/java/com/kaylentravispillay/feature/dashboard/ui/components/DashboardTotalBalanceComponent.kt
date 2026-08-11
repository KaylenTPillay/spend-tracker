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
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.kaylentravispillay.core.ui.R as CoreR
import com.kaylentravispillay.core.ui.theme.TrackerTheme
import com.kaylentravispillay.feature.dashboard.R
import com.kaylentravispillay.feature.dashboard.ui.state.DashboardTotalBalanceUiState

@Composable
internal fun DashboardTotalBalanceComponent(
    modifier: Modifier = Modifier,
    state: DashboardTotalBalanceUiState
) {
    OutlinedCard(
        modifier = modifier,
        shape = MaterialTheme.shapes.extraLarge,
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
                        painter = painterResource(CoreR.drawable.outlined_material_account_balance),
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
                        icon = painterResource(CoreR.drawable.filled_material_arrow_upward_alt),
                        total = state.income
                    )

                    DashboardTotalBalanceBreakdownComponent(
                        title = stringResource(R.string.spent_this_month),
                        icon = painterResource(CoreR.drawable.filled_material_arrow_downward_alt),
                        total = state.spent
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
    icon: Painter,
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
                painter = icon,
                contentDescription = null
            )
            Text(
                text = total,
                style = MaterialTheme.typography.labelLarge,
                color = MaterialTheme.colorScheme.onBackground
            )
        }
    }
}

@Preview
@Composable
private fun DashboardTotalBalanceComponent_Light_Preview() {
    val previewState = DashboardTotalBalanceUiState(
        balance = "R12,450.00",
        income = "R71,200.21",
        spent = "R38,200.76"
    )

    TrackerTheme {
        DashboardTotalBalanceComponent(
            modifier = Modifier.fillMaxWidth(),
            state = previewState
        )
    }
}

@Preview
@Composable
private fun DashboardTotalBalanceComponent_Dark_Preview() {
    val previewState = DashboardTotalBalanceUiState(
        balance = "R12,450.00",
        income = "R71,200.21",
        spent = "R38,200.76"
    )

    TrackerTheme(darkTheme = true) {
        DashboardTotalBalanceComponent(
            modifier = Modifier.fillMaxWidth(),
            state = previewState
        )
    }
}
