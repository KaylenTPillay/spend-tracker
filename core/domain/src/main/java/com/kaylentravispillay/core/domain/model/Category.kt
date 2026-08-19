package com.kaylentravispillay.core.domain.model

data class Category(
    val id: Int = 0,
    val name: String,
    val description: String?,
    val iconType: CategoryIconType
)
