package com.kaylentravispillay.core.data.local.database.converter

import com.kaylentravispillay.core.domain.model.TransactionType
import io.kotest.matchers.equals.shouldEqual
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.EnumSource
import org.junit.jupiter.params.provider.ValueSource

class TransactionConverterTest {
    @Nested
    inner class ToTransactionTypeTestSuite {
        @ParameterizedTest
        @ValueSource(strings = ["income", "INCOME", "iNcOmE"])
        fun `GIVEN valid case variations WHEN toTransactionType is called THEN parse case-insensitive type`(
            input: String
        ) {
            // Arrange
            val expectedType = TransactionType.Income

            // Act
            val actual = TransactionConverter.toTransactionType(input)

            // Assert
            expectedType shouldEqual actual
        }

        @Test
        fun `GIVEN invalid value WHEN toTransactionType is called THEN return Unknown`() {
            // Arrange
            val input = "Test-Value"
            val expectedType = TransactionType.Unknown

            // Act
            val actualType = TransactionConverter.toTransactionType(input)

            // Assert
            expectedType shouldEqual actualType
        }
    }

    @Nested
    inner class FromTransactionTypeSuite {
        @ParameterizedTest
        @EnumSource(value = TransactionType::class)
        fun `GIVEN type WHEN toTransactionType is called THEN return value`(type: TransactionType) {
            // Arrange
            val expectedValue = type.name

            // Act
            val actualValue = TransactionConverter.fromTransactionType(type)

            // Assert
            expectedValue shouldEqual actualValue
        }
    }
}
