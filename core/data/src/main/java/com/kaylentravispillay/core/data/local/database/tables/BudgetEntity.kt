package com.kaylentravispillay.core.data.local.database.tables

import androidx.room3.ColumnInfo
import androidx.room3.Entity
import androidx.room3.Index
import androidx.room3.PrimaryKey

@Entity(
    tableName = "budgets",
    indices = [
        Index("start_timestamp", unique = true)
    ]
)
internal data class BudgetEntity(
    @PrimaryKey(autoGenerate = true) val id: Int,
    @ColumnInfo("start_timestamp") val startTimestamp: Long,
    @ColumnInfo("spend_limit") val spendLimit: Int
)
