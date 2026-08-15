package com.kaylentravispillay.core.data.local.database.tables

import androidx.room3.ColumnInfo
import androidx.room3.Entity
import androidx.room3.ForeignKey
import androidx.room3.Index
import androidx.room3.PrimaryKey

@Entity(
    tableName = "budget_category_limits",
    foreignKeys = [
        ForeignKey(
            entity = BudgetEntity::class,
            parentColumns = ["id"],
            childColumns = ["budget_id"],
            onDelete = ForeignKey.CASCADE,
            onUpdate = ForeignKey.CASCADE
        ),
        ForeignKey(
            entity = CategoryEntity::class,
            parentColumns = ["id"],
            childColumns = ["category_id"],
            onDelete = ForeignKey.RESTRICT,
            onUpdate = ForeignKey.CASCADE
        )
    ],
    indices = [
        Index("budget_id"),
        Index("category_id")
    ]
)
internal data class BudgetCategoryLimitEntity(
    @PrimaryKey(autoGenerate = true) val id: Int,
    @ColumnInfo("budget_id") val budgetId: Int,
    @ColumnInfo("category_id") val categoryId: Int,
    @ColumnInfo("spend_limit") val spendLimit: Int
)
