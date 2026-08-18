package com.kaylentravispillay.feature.categories.domain.usecase

import app.cash.turbine.test
import com.kaylentravispillay.core.domain.model.Category
import com.kaylentravispillay.feature.categories.domain.repository.CategoryRepository
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
import org.junit.jupiter.api.Test

class ObserveCategoriesUseCaseTest {
    private lateinit var useCase: ObserveCategoriesUseCase
    private val mockRepository: CategoryRepository = mockk()

    @OptIn(ExperimentalCoroutinesApi::class)
    private val testDispatcher = UnconfinedTestDispatcher()

    @BeforeEach
    fun setup() {
        useCase = ObserveCategoriesUseCase(repository = mockRepository)
    }

    @AfterEach
    fun tearDown() {
        clearMocks(mockRepository)
    }

    @Test
    fun `WHEN useCase is invoked THEN collect expected category list`() = runTest(testDispatcher) {
        // Arrange
        val expectedCategoriesList = listOf(
            Category(
                id = 0,
                name = "Test-Category-Name-0",
                description = "Test-Category-Description-0",
                iconType = "Test-Icon-Type-0"
            ),
            Category(
                id = 1,
                name = "Test-Category-Name-1",
                description = "Test-Category-Description-1",
                iconType = "Test-Icon-Type-1"
            )
        )
        val testFlow = flowOf(expectedCategoriesList)

        every {
            mockRepository.observeCategories()
        } returns testFlow

        // Act
        val resultFlow = useCase()

        // Assert
        resultFlow.test {
            val actualCategoriesList = awaitItem()
            cancelAndIgnoreRemainingEvents()

            actualCategoriesList shouldEqual expectedCategoriesList
        }
    }
}
