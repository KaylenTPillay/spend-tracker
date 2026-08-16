package com.kaylentravispillay.core.data.local.database.daos

import androidx.room3.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.kaylentravispillay.core.data.local.database.TrackerDatabase
import com.kaylentravispillay.core.data.local.database.tables.CategoryEntity
import io.kotest.matchers.equals.shouldEqual
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.runTest
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import java.io.IOException

@RunWith(value = AndroidJUnit4::class)
class CategoryDaoTest {
    private lateinit var dao: CategoryDao
    private lateinit var database: TrackerDatabase

    @OptIn(ExperimentalCoroutinesApi::class)
    private val testDispatcher = UnconfinedTestDispatcher()

    @Before
    fun setup() {
        database = Room.inMemoryDatabaseBuilder(
            context = ApplicationProvider.getApplicationContext(),
            klass = TrackerDatabase::class.java
        ).allowMainThreadQueries().build()

        dao = database.getCategoryDao()
    }

    @After
    @Throws(IOException::class)
    fun tearDown() {
        database.close()
    }

    @Test
    fun givenNoCategories_whenGetCategoriesIsCalled_thenReturnEmptyList() =
        runTest(testDispatcher) {
            // Arrange
            val expectedList = emptyList<CategoryEntity>()

            // Act
            val actualList = dao.getCategories()

            // Assert
            actualList shouldEqual expectedList
        }

    @Test
    fun givenCategories_whenGetCategoriesIsCalled_thenReturnStoredCategories() =
        runTest(testDispatcher) {
            // Arrange
            val testCategoryOne = CategoryEntity(
                id = 0,
                name = "Test-Category-Name-One",
                description = "Test-Category-Description-One",
                iconType = "Test-Icon-Type-One"
            )
            val testCategoryTwo = CategoryEntity(
                id = 0,
                name = "Test-Category-Name-Two",
                description = "Test-Category-Description-Two",
                iconType = "Test-Icon-Type-Two"
            )

            val expectedList = listOf(
                testCategoryOne.copy(id = 1),
                testCategoryTwo.copy(id = 2)
            )

            // Act
            dao.addCategory(testCategoryOne)
            dao.addCategory(testCategoryTwo)

            val actualList = dao.getCategories()

            // Assert
            actualList shouldEqual expectedList
        }
}
