package com.kaylentravispillay.core.data.local.database.utils

import com.kaylentravispillay.core.data.local.database.daos.CategoryDao
import com.kaylentravispillay.core.data.local.database.tables.CategoryEntity
import com.kaylentravispillay.core.domain.model.CategoryIconType

internal suspend fun CategoryDao.seedCategories() {
    val testCategoryOne = CategoryEntity(
        id = 0,
        name = "Test-Category-Name-One",
        description = "Test-Category-Description-One",
        iconType = CategoryIconType.Label
    )
    val testCategoryTwo = CategoryEntity(
        id = 0,
        name = "Test-Category-Name-Two",
        description = "Test-Category-Description-Two",
        iconType = CategoryIconType.Label
    )

    addCategory(testCategoryOne)
    addCategory(testCategoryTwo)
}
