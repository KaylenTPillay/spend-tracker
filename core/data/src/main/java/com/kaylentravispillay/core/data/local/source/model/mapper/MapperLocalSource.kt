package com.kaylentravispillay.core.data.local.source.model.mapper

import com.kaylentravispillay.core.data.local.database.tables.TransactionEntity
import com.kaylentravispillay.core.domain.model.Transaction

internal object MapperLocalSource {
    fun Transaction.toEntity(): TransactionEntity {
        return TransactionEntity(
            id = id,
            timestamp = timestamp,
            amount = amount,
            title = title,
            type = type,
            categoryId = category.id
        )
    }
}
