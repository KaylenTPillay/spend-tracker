package com.kaylentravispillay.core.data.local.database.converter

import androidx.room3.ColumnTypeConverter
import com.kaylentravispillay.core.domain.model.CategoryIconType

object CategoryConverter {
    @ColumnTypeConverter
    fun toCategoryIconType(value: String): CategoryIconType {
        return CategoryIconType.entries.firstOrNull { type ->
            type.name.equals(value, ignoreCase = true)
        } ?: CategoryIconType.Label
    }

    @ColumnTypeConverter
    fun fromCategoryIconType(type: CategoryIconType): String {
        return type.name
    }
}
