package com.kaylentravispillay.feature.categories.data.repository

import app.cash.turbine.test
import com.kaylentravispillay.core.data.local.source.CategoryLocalSource
import com.kaylentravispillay.core.domain.model.Category
import com.kaylentravispillay.core.domain.model.CategoryIconType
import io.kotest.matchers.equals.shouldEqual
import io.mockk.clearMocks
import io.mockk.every
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.runTest
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test

class CategoryRepositoryImplTest {
    private lateinit var repository: CategoryRepositoryImpl
    private val mockLocalSource: CategoryLocalSource = mockk()

    @OptIn(ExperimentalCoroutinesApi::class)
    private val testDispatcher = UnconfinedTestDispatcher()

    @BeforeEach
    fun setup() {
        repository = CategoryRepositoryImpl(
            categoryLocalSource = mockLocalSource,
            dispatcher = testDispatcher
        )
    }

    @AfterEach
    fun tearDown() {
        clearMocks(mockLocalSource)
    }

    @Nested
    inner class ObserveCategoriesTestSuite {
        @Test
        fun `WHEN observeCategories is called THEN return expected flow`() =
            runTest(testDispatcher) {
                // Arrange
                val expectedCategoryList = listOf(
                    Category(
                        id = 0,
                        name = "Test-Category-Name-0",
                        description = "Test-Category-Description-0",
                        iconType = CategoryIconType.Label
                    ),
                    Category(
                        id = 1,
                        name = "Test-Category-Name-1",
                        description = "Test-Category-Description-1",
                        iconType = CategoryIconType.Label
                    )
                )
                val testFlow = flowOf(expectedCategoryList)

                every {
                    mockLocalSource.observeCategories()
                } returns testFlow

                // Act
                val resultFlow = repository.observeCategories()

                // Assert
                resultFlow.test {
                    val actualCategoryList = awaitItem()
                    cancelAndIgnoreRemainingEvents()

                    actualCategoryList shouldEqual expectedCategoryList
                }
            }
    }
}
