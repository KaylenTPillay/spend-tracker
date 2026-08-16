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
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.kaylentravispillay.core.ui.theme.TrackerTheme
import com.kaylentravispillay.feature.dashboard.R
import com.kaylentravispillay.feature.dashboard.ui.state.MonthlyBudgetUiState

@Composable
internal fun MonthlyBudget(
    modifier: Modifier = Modifier,
    state: MonthlyBudgetUiState
) {
    OutlinedCard(
        modifier = modifier,
        shape = MaterialTheme.shapes.medium,
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surface
        ),
        border = BorderStroke(
            width = 1.dp,
            color = MaterialTheme.colorScheme.outlineVariant
        )
    ) {
        Box(
            modifier = Modifier.padding(TrackerTheme.spacing.lg)
        ) {
            Column {
                Text(
                    text = stringResource(R.string.monthly_budget),
                    style = MaterialTheme.typography.labelLarge,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )

                Spacer(modifier = Modifier.height(TrackerTheme.spacing.sm))

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(
                        modifier = Modifier.alignByBaseline(),
                        text = state.usedAmount,
                        style = MaterialTheme.typography.headlineSmall,
                        color = MaterialTheme.colorScheme.onBackground
                    )

                    Text(
                        modifier = Modifier.alignByBaseline(),
                        text = stringResource(
                            R.string.monthly_budget_allocation,
                            state.allocation
                        ),
                        style = MaterialTheme.typography.titleSmall,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                }

                Spacer(modifier = Modifier.height(8.dp))

                LinearProgressIndicator(
                    progress = { state.usedProgress },
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(8.dp),
                    color = when {
                        state.usedProgress > 0.9f -> TrackerTheme.semanticColor.expense
                        state.usedProgress >= 0.8f -> TrackerTheme.semanticColor.warning
                        else -> MaterialTheme.colorScheme.primary
                    },
                    trackColor = MaterialTheme.colorScheme.surfaceVariant
                )

                Spacer(modifier = Modifier.height(8.dp))

                val budgetUsedPercentage = (state.usedProgress * 100).toInt()
                Text(
                    modifier = Modifier.align(Alignment.End),
                    text = stringResource(
                        R.string.monthly_budget_used,
                        budgetUsedPercentage
                    ),
                    style = MaterialTheme.typography.titleSmall,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
            }
        }
    }
}

@Preview
@Composable
private fun MonthlyBudgetPreview_Light() {
    val previewState = MonthlyBudgetUiState(
        allocation = "R999,999,999.99",
        usedAmount = "R999,999,999.99",
        usedProgress = .1F
    )
    TrackerTheme {
        MonthlyBudget(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            state = previewState
        )
    }
}

@Preview
@Composable
private fun MonthlyBudgetPreview_Dark() {
    val previewState = MonthlyBudgetUiState(
        allocation = "R999,999,999.99",
        usedAmount = "R999,999,999.99",
        usedProgress = .56F
    )
    TrackerTheme(darkTheme = true) {
        MonthlyBudget(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            state = previewState
        )
    }
}
