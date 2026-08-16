package com.kaylentravispillay.core.data.local.source.impl

import app.cash.turbine.test
import com.kaylentravispillay.core.data.local.database.daos.TransactionDao
import com.kaylentravispillay.core.data.local.source.model.FinancialSummaryLocal
import com.kaylentravispillay.core.data.local.source.model.mapper.MapperLocalSource.toEntity
import com.kaylentravispillay.core.domain.model.Category
import com.kaylentravispillay.core.domain.model.Transaction
import com.kaylentravispillay.core.domain.model.TransactionType
import io.kotest.matchers.equals.shouldEqual
import io.mockk.clearMocks
import io.mockk.coEvery
import io.mockk.coVerify
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

class TransactionLocalSourceImplTest {
    private lateinit var source: TransactionLocalSourceImpl
    private val mockTransactionDao: TransactionDao = mockk()
    @OptIn(ExperimentalCoroutinesApi::class)
    private val testDispatcher = UnconfinedTestDispatcher()

    @BeforeEach
    fun setup() {
        source = TransactionLocalSourceImpl(
            transactionDao = mockTransactionDao,
            dispatcher = testDispatcher
        )
    }

    @AfterEach
    fun tearDown() {
        clearMocks(mockTransactionDao)
    }

    @Nested
    inner class AddTransactionTestSuite {
        @Test
        fun `WHEN addTransaction is called THEN add transaction to dao with expected entity`() =
            runTest(testDispatcher) {
                // Arrange
                val inputTransaction = Transaction(
                    amount = 200,
                    title = "Test-Transaction-Title",
                    type = TransactionType.Unknown,
                    category = Category(
                        id = 2,
                        name = "Test-Transaction-Category-Name",
                        description = "Test-Description",
                        iconType = "Test-Transaction-Category-Icon-Type"
                    ),
                    timestamp = 1_000_000L
                )
                val expectedTransactionEntity = inputTransaction.toEntity()

                coEvery {
                    mockTransactionDao.addTransaction(any())
                } returns Unit

                // Act
                source.addTransaction(inputTransaction)

                // Assert
                coVerify(exactly = 1) {
                    mockTransactionDao.addTransaction(expectedTransactionEntity)
                }
            }
    }

    @Nested
    inner class ObserveFinancialSummaryTestSuite {
        @Test
        fun `WHEN observeFinancialSummary is called THEN observe flow of data from dao`() =
            runTest(testDispatcher) {
                // Arrange
                val inputStartMonth = 200L
                val expectedItem = FinancialSummaryLocal(
                    total = 100,
                    monthlyIncome = 200,
                    monthlyExpenses = 300
                )
                val testFlow = flowOf(expectedItem)

                every {
                    mockTransactionDao.observeFinancialSummaryProjection(inputStartMonth)
                } returns testFlow

                // Act
                val actualFlow = source.observeFinancialSummary(inputStartMonth)

                // Assert
                actualFlow.test {
                    val actualItem = awaitItem()
                    expectedItem shouldEqual actualItem

                    awaitComplete()
                }
            }
    }
}
