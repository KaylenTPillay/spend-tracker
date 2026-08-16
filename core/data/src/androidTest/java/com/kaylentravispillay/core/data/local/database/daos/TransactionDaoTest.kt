package com.kaylentravispillay.core.data.local.database.daos

import androidx.room3.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import app.cash.turbine.test
import com.kaylentravispillay.core.data.local.database.TrackerDatabase
import com.kaylentravispillay.core.data.local.database.tables.TransactionEntity
import com.kaylentravispillay.core.data.local.database.utils.seedCategories
import com.kaylentravispillay.core.data.local.source.model.FinancialSummaryLocal
import com.kaylentravispillay.core.domain.model.TransactionType
import io.kotest.matchers.equals.shouldEqual
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.runTest
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import java.io.IOException

@RunWith(AndroidJUnit4::class)
class TransactionDaoTest {
    private lateinit var dao: TransactionDao
    private lateinit var testDatabase: TrackerDatabase
    @OptIn(ExperimentalCoroutinesApi::class)
    private val testDispatcher = UnconfinedTestDispatcher()

    @Before
    fun setup() {
        testDatabase = Room.inMemoryDatabaseBuilder(
            context = ApplicationProvider.getApplicationContext(),
            klass = TrackerDatabase::class.java
        ).allowMainThreadQueries().build()

        dao = testDatabase.getTransactionDao()
    }

    @After
    @Throws(IOException::class)
    fun tearDown() {
        testDatabase.close()
    }

    @Test
    fun givenNoTransactions_whenObserveFinancialSummaryProjectIsCalled_thenReturnEmptyFinancialSummary() =
        runTest(testDispatcher) {
            // Arrange
            val inputMonthStart = 1_000_000L
            val expectedSummary = FinancialSummaryLocal(
                total = null,
                monthlyIncome = null,
                monthlyExpenses = null
            )

            // Act
            val resultFlow = dao.observeFinancialSummaryProjection(inputMonthStart)

            // Assert
            resultFlow.test {
                val actualSummary = awaitItem()
                cancelAndIgnoreRemainingEvents()

                actualSummary shouldEqual expectedSummary
            }
        }

    @Test
    fun givenVariousTransactions_whenObserveFinancialSummaryProjectIsCalled_thenReturnCorrectFinancialSummary() =
        runTest(testDispatcher) {
            // Arrange
            val inputMonthStart = 1_000_000L

            // This transaction is an income which occurred in the period.
            val testTransactionOne = TransactionEntity(
                id = 0,
                timestamp = 1_000_500L,
                amount = 100,
                title = "Test-Transaction-One-Title",
                type = TransactionType.Income,
                categoryId = 1
            )
            // This transaction is an income which occurred in the period
            val testTransactionTwo = TransactionEntity(
                id = 0,
                timestamp = 1_302_000L,
                amount = 400,
                title = "Test-Transaction-Two-Title",
                type = TransactionType.Income,
                categoryId = 1
            )
            // This transaction is an expense which occurred in the period
            val testTransactionThree = TransactionEntity(
                id = 0,
                timestamp = 1_456_000L,
                amount = 200,
                title = "Test-Transaction-Three-Title",
                type = TransactionType.Expense,
                categoryId = 1
            )
            // This transaction is an income which occurred before the period
            val testTransactionFour = TransactionEntity(
                id = 0,
                timestamp = 500_000L,
                amount = 100,
                title = "Test-Transaction-Four-Title",
                type = TransactionType.Expense,
                categoryId = 1
            )
            // This transaction is an income which occurred before the period
            val testTransactionFive = TransactionEntity(
                id = 0,
                timestamp = 200_000L,
                amount = 550,
                title = "Test-Transaction-Five-Title",
                type = TransactionType.Income,
                categoryId = 1
            )
            // This transaction is unknown which occurred before the period
            val testTransactionSix = TransactionEntity(
                id = 0,
                timestamp = 100_000L,
                amount = 1000,
                title = "Test-Transaction-Six-Title",
                type = TransactionType.Unknown,
                categoryId = 1
            )

            val expectedSummary = FinancialSummaryLocal(
                total = 750,
                monthlyIncome = 500,
                monthlyExpenses = 200
            )

            // Act
            testDatabase.getCategoryDao().seedCategories()

            dao.addTransaction(testTransactionOne)
            dao.addTransaction(testTransactionTwo)
            dao.addTransaction(testTransactionThree)
            dao.addTransaction(testTransactionFour)
            dao.addTransaction(testTransactionFive)
            dao.addTransaction(testTransactionSix)

            val resultFlow = dao.observeFinancialSummaryProjection(inputMonthStart)

            // Assert
            resultFlow.test {
                val actualSummary = awaitItem()
                cancelAndIgnoreRemainingEvents()

                actualSummary shouldEqual expectedSummary
            }
        }
}
