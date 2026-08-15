package com.kaylentravispillay.core.data.local.source.model

import androidx.room3.ColumnInfo

data class FinancialSummaryLocal(
    @ColumnInfo("total") val total: Int?,
    @ColumnInfo("monthly_income") val monthlyIncome: Int?,
    @ColumnInfo("monthly_expenses") val monthlyExpenses: Int?
)
