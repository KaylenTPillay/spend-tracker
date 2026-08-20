package com.kaylentravispillay.feature.categories.domain.model

import com.kaylentravispillay.core.domain.model.CategoryIconType

internal enum class DefaultCategories(
    val titleKey: String,
    val descriptionKey: String,
    val icon: CategoryIconType
) {
    Grocery(
        titleKey = "grocery_title",
        descriptionKey = "grocery_description",
        icon = CategoryIconType.ShoppingCart
    ),
    Dinning(
        titleKey = "dinning_title",
        descriptionKey = "dinning_description",
        icon = CategoryIconType.Restaurant
    ),
    Coffee(
        titleKey = "coffee_title",
        descriptionKey = "coffee_description",
        icon = CategoryIconType.LocalCafe
    ),
    Transport(
        titleKey = "transport_title",
        descriptionKey = "transport_description",
        icon = CategoryIconType.DirectionsCar
    ),
    Housing(
        titleKey = "housing_title",
        descriptionKey = "housing_description",
        icon = CategoryIconType.Home
    ),
    Shopping(
        titleKey = "shopping_title",
        descriptionKey = "shopping_description",
        icon = CategoryIconType.ShoppingBag
    ),
    Subscription(
        titleKey = "subscriptions_title",
        descriptionKey = "subscriptions_description",
        icon = CategoryIconType.Subscriptions
    ),
    Entertainment(
        titleKey = "entertainment_title",
        descriptionKey = "entertainment_description",
        icon = CategoryIconType.Movie
    ),
    Health(
        titleKey = "health_title",
        descriptionKey = "health_description",
        icon = CategoryIconType.MedicalServices
    ),
    Pets(
        titleKey = "pets_title",
        descriptionKey = "pets_description",
        icon = CategoryIconType.Pets
    )
}
