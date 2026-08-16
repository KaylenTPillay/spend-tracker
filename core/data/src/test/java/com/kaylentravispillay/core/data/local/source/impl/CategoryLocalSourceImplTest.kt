package com.kaylentravispillay.core.data.local.source.impl

import com.kaylentravispillay.core.data.local.database.daos.CategoryDao
import com.kaylentravispillay.core.data.local.database.tables.CategoryEntity
import com.kaylentravispillay.core.data.local.source.model.mapper.MapperLocalSource.toEntity
import com.kaylentravispillay.core.data.local.source.model.mapper.MapperLocalSource.toLocal
import com.kaylentravispillay.core.domain.model.Category
import io.kotest.matchers.result.shouldBeFailure
import io.kotest.matchers.result.shouldBeSuccess
import io.mockk.clearMocks
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.CancellationException
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.runTest
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

class CategoryLocalSourceImplTest {
    private lateinit var source: CategoryLocalSourceImpl
    private val mockCategoryDao: CategoryDao = mockk()

    @OptIn(ExperimentalCoroutinesApi::class)
    private val testDispatcher = UnconfinedTestDispatcher()

    @BeforeEach
    fun setup() {
        source = CategoryLocalSourceImpl(
            categoryDao = mockCategoryDao,
            dispatcher = testDispatcher
        )
    }

    @AfterEach
    fun tearDown() {
        clearMocks(mockCategoryDao)
    }

    @Nested
    inner class AddCategoryTestSuite {
        @Test
        fun `WHEN addCategory is called THEN add category entity to dao`() =
            runTest(testDispatcher) {
                // Arrange
                val input = Category(
                    id = 2,
                    name = "Test-Category",
                    description = "Test-Description",
                    iconType = "Test-Icon-Type"
                )
                val expectedEntity = input.toEntity()

                coEvery {
                    mockCategoryDao.addCategory(any())
                } returns Unit

                // Act
                source.addCategory(input)

                // Assert
                coVerify(exactly = 1) {
                    mockCategoryDao.addCategory(expectedEntity)
                }
            }
    }

    @Nested
    inner class GetCategoriesTestSuite {
        @Test
        fun `WHEN getCategories is called THEN return successful result with mapped data`() =
            runTest(testDispatcher) {
                // Arrange
                val testCategoryEntities = listOf(
                    CategoryEntity(
                        id = 1,
                        name = "Test-Category-1",
                        description = "Test-Description-1",
                        iconType = "Test-Category-Icon-1"
                    ),
                    CategoryEntity(
                        id = 2,
                        name = "Test-Category-2",
                        description = "Test-Description-2",
                        iconType = "Test-Category-Icon-2"
                    )
                )
                val expectedLocalCollection =
                    testCategoryEntities.map { entity -> entity.toLocal() }

                coEvery {
                    mockCategoryDao.getCategories()
                } returns testCategoryEntities

                // Act
                val actualResult = source.getCategories()

                // Assert
                actualResult shouldBeSuccess expectedLocalCollection
            }

        @Test
        fun `GIVEN generic exception WHEN getCategories is called THEN return failure result with generic exception`() =
            runTest(testDispatcher) {
                // Arrange
                val expectedException = Exception("Something went wrong")

                coEvery {
                    mockCategoryDao.getCategories()
                } throws expectedException

                // Act
                val actualResult = source.getCategories()

                // Assert
                actualResult shouldBeFailure expectedException
            }

        @Test
        fun `GIVEN cancellation exception WHEN getCategories is called THEN throw cancellation exception`() =
            runTest(testDispatcher) {
                // Arrange
                val expectedException = CancellationException("Coroutine Cancelled")

                coEvery {
                    mockCategoryDao.getCategories()
                } throws expectedException

                // Act & Assert
                assertThrows<CancellationException> {
                    source.getCategories()
                }
            }
    }
}
