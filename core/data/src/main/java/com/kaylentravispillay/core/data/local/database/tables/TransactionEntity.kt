package com.kaylentravispillay.core.data.local.database.tables

import androidx.room3.ColumnInfo
import androidx.room3.Entity
import androidx.room3.ForeignKey
import androidx.room3.Index
import androidx.room3.PrimaryKey
import com.kaylentravispillay.core.domain.model.TransactionType

@Entity(
    tableName = "transactions",
    foreignKeys = [
        ForeignKey(
            entity = CategoryEntity::class,
            parentColumns = ["id"],
            childColumns = ["category_id"],
            onDelete = ForeignKey.RESTRICT,
            onUpdate = ForeignKey.CASCADE
        )
    ],
    indices = [
        Index("category_id"),
        Index("timestamp")
    ]
)
internal data class TransactionEntity(
    @PrimaryKey(autoGenerate = true) val id: Int,
    @ColumnInfo("timestamp") val timestamp: Long,
    @ColumnInfo("amount") val amount: Int,
    @ColumnInfo("title") val title: String,
    @ColumnInfo("type") val type: TransactionType,
    @ColumnInfo("category_id") val categoryId: Int
)
