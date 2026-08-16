package com.kaylentravispillay.core.data.local.source.model.mapper

import com.kaylentravispillay.core.data.local.database.tables.CategoryEntity
import com.kaylentravispillay.core.data.local.database.tables.TransactionEntity
import com.kaylentravispillay.core.data.local.source.model.mapper.MapperLocalSource.toEntity
import com.kaylentravispillay.core.data.local.source.model.mapper.MapperLocalSource.toLocal
import com.kaylentravispillay.core.domain.model.Category
import com.kaylentravispillay.core.domain.model.Transaction
import com.kaylentravispillay.core.domain.model.TransactionType
import io.kotest.matchers.equals.shouldEqual
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.EnumSource

class MapperLocalSourceTest {
    @Nested
    inner class TransactionToEntityTestSuite {
        @ParameterizedTest
        @EnumSource(value = TransactionType::class)
        fun `GIVEN transaction with various types WHEN toEntity is called THEN return mapped TransactionEntity`(
            transactionType: TransactionType
        ) {
            // Arrange
            val input = Transaction(
                id = 1,
                amount = 100,
                title = "Test-Transaction-Title",
                type = transactionType,
                category = Category(
                    id = 2,
                    name = "Test-Category-Name",
                    iconType = "Test-Category-Icon-Type"
                ),
                timestamp = 1_000_000L
            )
            val expectedEntity = TransactionEntity(
                id = 1,
                timestamp = 1_000_000L,
                amount = 100,
                title = "Test-Transaction-Title",
                type = transactionType,
                categoryId = 2
            )

            // Act
            val actualEntity = input.toEntity()

            // Assert
            expectedEntity shouldEqual actualEntity
        }
    }

    @Nested
    inner class CategoryToEntityTestSuite {
        @Test
        fun `WHEN toEntity is called THEN return correctly mapped entity`() {
            // Arrange
            val input = Category(
                id = 2,
                name = "Test-Category-Name",
                iconType = "Test-Icon-Type"
            )
            val expectedEntity = CategoryEntity(
                id = 2,
                name = "Test-Category-Name",
                iconType = "Test-Icon-Type"
            )

            // Act
            val actualEntity = input.toEntity()

            // Assert
            expectedEntity shouldEqual actualEntity
        }
    }

    @Nested
    inner class CategoryEntityToLocalTestSuite {
        @Test
        fun `WHEN toLocal is called THEN return correctly mapped local`() {
            // Arrange
            val input = CategoryEntity(
                id = 2,
                name = "Test-Category-Name",
                iconType = "Test-Category-Icon-Type"
            )
            val expectedLocal = Category(
                id = 2,
                name = "Test-Category-Name",
                iconType = "Test-Category-Icon-Type"
            )

            // Act
            val actualLocal = input.toLocal()

            // Assert
            expectedLocal shouldEqual actualLocal
        }
    }
}
