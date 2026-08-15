package com.kaylentravispillay.core.data.local.database.daos

import androidx.room3.Dao
import androidx.room3.Insert
import androidx.room3.Query
import androidx.room3.Upsert
import com.kaylentravispillay.core.data.local.database.tables.TransactionEntity
import com.kaylentravispillay.core.data.local.source.model.FinancialSummaryLocal
import kotlinx.coroutines.flow.Flow

@Dao
internal interface TransactionDao {
    @Upsert
    suspend fun addTransaction(transaction: TransactionEntity)

    @Query("""
        SELECT
            SUM(CASE WHEN type = 'Income' THEN amount ELSE -amount END) AS total,
            SUM(CASE WHEN type = 'Income' AND timestamp >= :monthStart THEN amount ELSE 0 END) AS monthly_income,
            SUM(CASE WHEN type = 'Expense' AND timestamp >= :monthStart THEN amount ELSE 0 END) AS monthly_expenses
        FROM transactions
    """)
    fun observeFinancialSummaryProjection(monthStart: Long): Flow<FinancialSummaryLocal>
}
