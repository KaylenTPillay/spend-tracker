package com.kaylentravispillay.core.data.local.database.converter

import androidx.room3.ColumnTypeConverter
import com.kaylentravispillay.core.domain.model.TransactionType

object TransactionConverter {
    @ColumnTypeConverter
    fun toTransactionType(value: String): TransactionType {
        return TransactionType.entries.firstOrNull { type ->
            type.name.equals(value, ignoreCase = true)
        } ?: TransactionType.Unknown
    }

    @ColumnTypeConverter
    fun fromTransactionType(type: TransactionType): String {
        return type.name
    }
}
