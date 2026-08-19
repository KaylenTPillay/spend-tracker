package com.kaylentravispillay.core.data.local.database

import androidx.room3.ColumnTypeConverters
import androidx.room3.Database
import androidx.room3.RoomDatabase
import com.kaylentravispillay.core.data.local.database.converter.CategoryConverter
import com.kaylentravispillay.core.data.local.database.converter.TransactionConverter
import com.kaylentravispillay.core.data.local.database.daos.CategoryDao
import com.kaylentravispillay.core.data.local.database.daos.TransactionDao
import com.kaylentravispillay.core.data.local.database.tables.BudgetCategoryLimitEntity
import com.kaylentravispillay.core.data.local.database.tables.BudgetEntity
import com.kaylentravispillay.core.data.local.database.tables.CategoryEntity
import com.kaylentravispillay.core.data.local.database.tables.TransactionEntity

@Database(
    entities = [
        TransactionEntity::class,
        CategoryEntity::class,
        BudgetEntity::class,
        BudgetCategoryLimitEntity::class
    ],
    version = 1
)
@ColumnTypeConverters(
    value = [
        TransactionConverter::class,
        CategoryConverter::class
    ]
)
internal abstract class TrackerDatabase : RoomDatabase() {
    abstract fun getTransactionDao(): TransactionDao

    abstract fun getCategoryDao(): CategoryDao

    companion object {
        internal const val DATABASE_NAME = "tracker_database"
    }
}
