package com.kaylentravispillay.feature.categories.data.provider

import android.content.Context
import com.kaylentravispillay.core.domain.provider.StringProvider
import com.kaylentravispillay.feature.categories.R
import com.kaylentravispillay.feature.categories.domain.model.DefaultCategories
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

internal class AndroidCategoryStringProvider @Inject constructor(
    @ApplicationContext private val applicationContext: Context
) : StringProvider{
    private val lookup: Map<String, Int> = mapOf(
        DefaultCategories.Grocery.titleKey to R.string.category_default_groceries_title,
        DefaultCategories.Grocery.descriptionKey to R.string.category_default_groceries_description,
        DefaultCategories.Dinning.titleKey to R.string.category_default_dinning_title,
        DefaultCategories.Dinning.descriptionKey to R.string.category_default_dinning_description,
        DefaultCategories.Coffee.titleKey to R.string.category_default_coffee_title,
        DefaultCategories.Coffee.descriptionKey to R.string.category_default_coffee_description,
        DefaultCategories.Transport.titleKey to R.string.category_default_transport_title,
        DefaultCategories.Transport.descriptionKey to R.string.category_default_transport_description,
        DefaultCategories.Housing.titleKey to R.string.category_default_housing_title,
        DefaultCategories.Housing.descriptionKey to R.string.category_default_housing_description,
        DefaultCategories.Shopping.titleKey to R.string.category_default_shopping_title,
        DefaultCategories.Shopping.descriptionKey to R.string.category_default_shopping_description,
        DefaultCategories.Subscription.titleKey to R.string.category_default_subscription_title,
        DefaultCategories.Subscription.descriptionKey to R.string.category_default_subscription_description,
        DefaultCategories.Entertainment.titleKey to R.string.category_default_entertainment_title,
        DefaultCategories.Entertainment.descriptionKey to R.string.category_default_entertainment_description,
        DefaultCategories.Health.titleKey to R.string.category_default_health_title,
        DefaultCategories.Health.descriptionKey to R.string.category_default_health_description,
        DefaultCategories.Pets.titleKey to R.string.category_default_pet_title,
        DefaultCategories.Pets.descriptionKey to R.string.category_default_pet_description
    )

    override fun getString(key: String): String {
        val resId = lookup[key] ?: return ""
        return applicationContext.getString(resId)
    }
}
