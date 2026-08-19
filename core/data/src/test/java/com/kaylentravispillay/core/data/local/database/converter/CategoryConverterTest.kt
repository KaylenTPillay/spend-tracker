package com.kaylentravispillay.core.data.local.database.converter

import com.kaylentravispillay.core.domain.model.CategoryIconType
import io.kotest.matchers.equals.shouldEqual
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.EnumSource
import org.junit.jupiter.params.provider.ValueSource

class CategoryConverterTest {
    @Nested
    inner class ToCategoryIconTypeTestSuite {
        @Test
        fun `GIVEN value is not valid type WHEN toCategoryIconType is called THEN return default`() {
            // Arrange
            val inputValue = "Test-Invalid-Type"

            val expectedIconType = CategoryIconType.Label

            // Act
            val actualIconType = CategoryConverter.toCategoryIconType(inputValue)

            // Assert
            expectedIconType shouldEqual actualIconType
        }

        @ParameterizedTest
        @ValueSource(strings = ["shoppingCart", "ShoppingCart", "ShOpPiNgCaRt"])
        fun `GIVEN valid case variations WHEN toCategoryIconType is called THEN parse case-insensitive type`(
            input: String
        ) {
            // Arrange
            val expectedType = CategoryIconType.ShoppingCart

            // Act
            val actual = CategoryConverter.toCategoryIconType(input)

            // Assert
            expectedType shouldEqual actual
        }
    }

    @Nested
    inner class FromCategoryIconTypeTestSuite {
        @ParameterizedTest
        @EnumSource(CategoryIconType::class)
        fun `GIVEN various type input WHEN fromCategoryIconType is called THEN return name`(
            input: CategoryIconType
        ) {
            // Arrange
            val expectedName = input.name

            // Act
            val actualName = CategoryConverter.fromCategoryIconType(input)

            // Assert
            expectedName shouldEqual actualName
        }
    }
}
