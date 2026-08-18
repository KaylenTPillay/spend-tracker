package com.kaylentravispillay.core.ui.state

enum class CategoryIconUiState(val key: String) {
    ShoppingCart("shopping_cart"),
    Restaurant("restaurant"),
    LocalCafe("local_cafe"),
    Home("home"),
    DirectionsCar("directions_car"),
    Flights("flights"),
    MedicalServices("medical_services"),
    School("school"),
    Bolt("bolt"),
    ShoppingBag("shopping_bag"),
    Subscriptions("subscriptions"),
    Movie("movie"),
    FitnessCentre("fitness_centre"),
    Pets("pets"),
    TrendUp("trend_up"),
    Label("label");

    companion object {
        private val lookup = entries.associateBy { entry -> entry.key }
        fun fromValue(value: String) = lookup[value] ?: Label
    }
}
