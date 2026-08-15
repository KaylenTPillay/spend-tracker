package com.kaylentravispillay.core.data.local.source

import com.kaylentravispillay.core.data.local.source.model.FinancialSummaryLocal
import com.kaylentravispillay.core.domain.model.Transaction
import kotlinx.coroutines.flow.Flow

interface TransactionLocalSource {
    suspend fun addTransaction(transaction: Transaction)

    fun observeFinancialSummary(monthStart: Long): Flow<FinancialSummaryLocal>
}
