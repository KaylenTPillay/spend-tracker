package com.kaylentravispillay.core.data.local.database.daos

import com.kaylentravispillay.core.data.local.database.tables.CategoryEntity

internal suspend fun CategoryDao.seedCategories() {
    val testCategoryOne = CategoryEntity(
        id = 0,
        name = "Test-Category-Name-One",
        iconType = "Test-Icon-Type-One"
    )
    val testCategoryTwo = CategoryEntity(
        id = 0,
        name = "Test-Category-Name-Two",
        iconType = "Test-Icon-Type-Two"
    )

    addCategory(testCategoryOne)
    addCategory(testCategoryTwo)
}
