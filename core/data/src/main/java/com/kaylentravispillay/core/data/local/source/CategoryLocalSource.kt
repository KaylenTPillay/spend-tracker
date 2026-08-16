package com.kaylentravispillay.core.data.local.source

import com.kaylentravispillay.core.domain.model.Category

interface CategoryLocalSource {
    suspend fun addCategory(category: Category)
}
