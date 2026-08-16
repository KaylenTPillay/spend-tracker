package com.kaylentravispillay.core.data.local.database.daos

import androidx.room3.Dao
import androidx.room3.Query
import androidx.room3.Upsert
import com.kaylentravispillay.core.data.local.database.tables.CategoryEntity

@Dao
internal interface CategoryDao {
    @Upsert
    suspend fun addCategory(category: CategoryEntity)

    @Query("""
        SELECT * 
        FROM categories
    """)
    suspend fun getCategories(): List<CategoryEntity>
}
