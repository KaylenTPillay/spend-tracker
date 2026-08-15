package com.kaylentravispillay.core.data.local.source.impl

import com.kaylentravispillay.core.common.annotations.IoDispatcher
import com.kaylentravispillay.core.data.local.database.daos.TransactionDao
import com.kaylentravispillay.core.data.local.source.TransactionLocalSource
import com.kaylentravispillay.core.data.local.source.model.FinancialSummaryLocal
import com.kaylentravispillay.core.data.local.source.model.mapper.MapperLocalSource.toEntity
import com.kaylentravispillay.core.domain.model.Transaction
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.withContext
import javax.inject.Inject

internal class TransactionLocalSourceImpl @Inject constructor(
    private val transactionDao: TransactionDao,
    @IoDispatcher private val dispatcher: CoroutineDispatcher
) : TransactionLocalSource {
    override suspend fun addTransaction(transaction: Transaction) {
        withContext(dispatcher) {
            transactionDao.addTransaction(transaction.toEntity())
        }
    }

    override fun observeFinancialSummary(monthStart: Long): Flow<FinancialSummaryLocal> {
        return transactionDao.observeFinancialSummaryProjection(monthStart)
            .flowOn(dispatcher)
    }
}
