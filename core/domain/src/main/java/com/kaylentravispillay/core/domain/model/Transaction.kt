package com.kaylentravispillay.core.domain.model

data class Transaction(
    val id: Int = 0,
    val amount: Int,
    val title: String,
    val type: TransactionType,
    val category: Category,
    val timestamp: Long
)
