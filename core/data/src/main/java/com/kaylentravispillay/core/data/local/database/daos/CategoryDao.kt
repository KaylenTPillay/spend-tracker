package com.kaylentravispillay.core.data.local.database.daos

import androidx.room3.Dao
import androidx.room3.Query
import androidx.room3.Upsert
import com.kaylentravispillay.core.data.local.database.tables.CategoryEntity
import kotlinx.coroutines.flow.Flow

@Dao
internal interface CategoryDao {
    @Upsert
    suspend fun addCategory(category: CategoryEntity)

    @Query("""
        SELECT * 
        FROM categories
    """)
    fun observeCategories(): Flow<List<CategoryEntity>>
}
